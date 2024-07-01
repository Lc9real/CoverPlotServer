
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;
import java.util.List;

public class ServerThread extends Thread
{
    private Socket socket;
    private Connection connection;
    public InetAddress address;
    private User user;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ServerThread(Socket socket, InetAddress address)
    {
        this.socket = socket;
        this.address = address;
        connection = SQLSystem.sqlSetup();
    }


    @Override
    public void run()
    {
        try
        {
            // Test not Final
            user = User.getUserInfo(1, connection);
            user.id = 1;
            // Test not finale


            outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            inputStream = new ObjectInputStream(this.socket.getInputStream());

            while (true) 
            { 
                
                Message message = (Message)inputStream.readObject();
                Message answer = InvokeMethode(message);
                if (message.data != null)
                {
                    outputStream.writeObject(answer);
                    outputStream.flush();
                }
                
            }
        }
        catch(Exception e) { e.printStackTrace(); }
        
    }


    private Message InvokeMethode(Message message) throws SQLException
    {
        Message answer = new Message();
        answer.id = message.id;
        switch((String)message.data)
        {
            case "addPost":
            {
                Post post = (Post)message.args[0];
                post.userID = user.id;
                Post.addPost(post, connection);
                break;
            }
            case "getPostInfo":
            {
                answer.data = Post.getPostInfo((int)message.args[0], connection);
                break;
            }
            case "getPosts":
            {
                answer.data = Post.getPosts(user.id, (int)message.args[0], (Integer)message.args[1], (SortBy)message.args[2], connection);
                break;
            }
            case "addComment":
            {
                Comment comment = (Comment)message.args[0];
                comment.userID = user.id;
                answer.data = Comment.addComment(comment, connection);
                break;
            }
            case "getCommentInfo":
            {
                answer.data = Comment.getCommentInfo((int)message.args[0], connection);
                break;
            }
            case "getComments":
            {
                answer.data = Comment.getComments((int)message.args[0], (int)message.args[1], (SortBy)message.args[2], (int)message.args[3],connection);
                break;
            }
            case "getSubComments":
            {
                answer.data = Comment.getSubComments((int)message.args[0], (int)message.args[1], (SortBy)message.args[2], connection);
                break;
            }
            case "getAllSubComments":
            {   
                answer.data = Comment.getAllSubComments((List<Comment>)message.args[0], 0, connection);
                break;
            }
            case "getSeriesID":
            {
                answer.data = Series.getSeriesID((String)message.args[0], connection);
                break;
            }
            case "getSeriesInfo":
            {
                answer.data = Series.getSeriesInfo((int)message.args[0], connection);
                break;
            }
            case "getUserIDFromUsername":
            {
                answer.data = User.getUserIDFromUsername((String)message.args[0], connection);
                break;
            }
            case "getUserIDFromEmail":
            {
                answer.data = User.getUserIDFromEmail((String)message.args[0], connection);
                break;
            }
            case "getUserInfo":
            {
                answer.data = User.getUserInfo((int)message.args[0], connection);
                break;
            }
            case "addUser":
            {
                User userToAdd = (User)message.args[0];
                User.addUser(userToAdd, (int)message.args[1], connection);
                answer.data = null;
                break;
            }
            case "addUserEpisode":
            {
                UserEpisode userEpisode = (UserEpisode)message.args[0];
                userEpisode.userID = user.id;
                UserEpisode.addUserEpisode(userEpisode, connection);
                answer.data = null;
                break;
            }
            case "logIn":
            {
                if(User.checkPassword((String)message.args[0], (int)message.args[1], connection))
                {
                    user = User.getUserInfo(User.getUserIDFromEmail((String)message.args[0], connection), connection);
                    answer.data = true;
                }
                else
                {
                    answer.data = false;
                }
                break;
            }
            case "getJoinedSeries":
            {
                answer.data = UserEpisode.getJoinedSeries(user, connection);
                break;
            }
        }

        return answer;
    }
}
