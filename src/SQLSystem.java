import java.sql.*;
import java.util.List;

public class SQLSystem {
    
    private static Connection connection;


    public static Connection sqlSetup()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:Database/CoverPlot.db");
            // Creates Tables if not already there

            UserEpisode.addUserEpisode(new UserEpisode(1, 1, 12, 12), connection);
            UserEpisode.addUserEpisode(new UserEpisode(1, 2, 12, 12), connection);
            UserEpisode.addUserEpisode(new UserEpisode(1, 3, 12, 12), connection);


            createTables();
            
            return connection;
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    


    public static void testPosts() throws SQLException
    {
        User.addUser(new User("Test", "test@gmail.com", ""), "12345678".hashCode(), connection);
            
        // Add series
        Series.addSeries(new Series("Naruto", "Naruto", ""), connection);
        Series.addSeries(new Series("Edgerunner", "Edging the runners", ""), connection);
        Series.addSeries(new Series("Arcane", "Psyco", ""), connection);

        // Add test posts and comments for the "Naruto" subreddit
        Post.addPost(new Post(1, 1, 1, 1, "Is Naruto Gay?", "What do you think?"), connection);
        Comment.addComment(new Comment(1, 1, null, "He kinda is"), connection);
        Comment.addComment(new Comment(1, 1, 1, "WTF"), connection);
        Comment.addComment(new Comment(1, 1, 1, "Yeah, I think the same"), connection);
        Comment.addComment(new Comment(1, 1, 2, "You dumb guy he is right"), connection);
        Comment.addComment(new Comment(1, 1, 4, "This guy gets it ^"), connection);

        Post.addPost(new Post(1, 1, 2, 1, "Sasuke's Emo Phase", "Why so serious?"), connection);
        Comment.addComment(new Comment(1, 2, null, "Because life is pain!"), connection);

        Post.addPost(new Post(1, 1, 3, 1, "Ramen vs Pizza", "Which one would Naruto choose?"), connection);
        Comment.addComment(new Comment(1, 3, null, "Ramen all the way!"), connection);
        Comment.addComment(new Comment(1, 3, null, "Pizza for life"), connection);

        Post.addPost(new Post(1, 1, 4, 1, "Kakashi's Mask", "What's behind it?"), connection);
        Comment.addComment(new Comment(1, 4, null, "Probably another mask"), connection);
        Comment.addComment(new Comment(1, 4, null, "A face!"), connection);

        Post.addPost(new Post(1, 1, 5, 1, "Naruto's IQ", "Is it over 9000?"), connection);
        Comment.addComment(new Comment(1, 5, null, "LOL, definitely not"), connection);
        Comment.addComment(new Comment(1, 5, null, "Maybe in Sage Mode"), connection);

        // Add test posts and comments for the "Edgerunner" subreddit
        Post.addPost(new Post(2, 1, 1, 1, "Edge Running Techniques", "What's your favorite?"), connection);
        Comment.addComment(new Comment(1, 6, null, "I love the classic slide"), connection);

        Post.addPost(new Post(2, 1, 2, 1, "Best Cyberware", "What's the must-have?"), connection);
        Comment.addComment(new Comment(1, 7, null, "Mantis Blades for sure!"), connection);

        Post.addPost(new Post(2, 1, 3, 1, "Night City Secrets", "Share your findings"), connection);
        Comment.addComment(new Comment(1, 8, null, "Found a hidden stash!"), connection);
        Comment.addComment(new Comment(1, 8, null, "Tell us more!"), connection);

        Post.addPost(new Post(2, 1, 4, 1, "V's Love Interests", "Who did you choose?"), connection);
        Comment.addComment(new Comment(1, 9, null, "Judy all the way"), connection);
        Comment.addComment(new Comment(1, 9, null, "Panam is better"), connection);

        Post.addPost(new Post(2, 1, 5, 1, "Cyberpsychosis", "How to avoid it?"), connection);
        Comment.addComment(new Comment(1, 10, null, "Stay chill, choom"), connection);

        // Add test posts and comments for the "Arcane" subreddit
        Post.addPost(new Post(3, 1, 1, 1, "Jinx's Crazy Ideas", "What's the craziest?"), connection);
        Comment.addComment(new Comment(1, 11, null, "Turning a toy into a weapon"), connection);
        Comment.addComment(new Comment(1, 11, null, "Her whole life is crazy"), connection);

        Post.addPost(new Post(3, 1, 2, 1, "Vi vs Caitlyn", "Who would win?"), connection);
        Comment.addComment(new Comment(1, 12, null, "Vi's got the brawn"), connection);
        Comment.addComment(new Comment(1, 12, null, "Caitlyn's got the brains"), connection);

        Post.addPost(new Post(3, 1, 3, 1, "Best Hextech Invention", "What's the coolest?"), connection);
        Comment.addComment(new Comment(1, 13, null, "The Hextech Gauntlets"), connection);

        Post.addPost(new Post(3, 1, 4, 1, "Favorite Arcane Episode", "Which one and why?"), connection);
        Comment.addComment(new Comment(1, 14, null, "The finale was epic"), connection);

        Post.addPost(new Post(3, 1, 5, 1, "Arcane's Music", "Best track?"), connection);
        Comment.addComment(new Comment(1, 15, null, "Enemy by Imagine Dragons"), connection);
        Comment.addComment(new Comment(1, 15, null, "All of it is amazing"), connection);

        Post.addPost(new Post(3, 1, 6, 1, "Jayce's Hammer", "Can I have one?"), connection);
        Comment.addComment(new Comment(1, 16, null, "Only if you're worthy"), connection);

        Post.addPost(new Post(3, 1, 7, 1, "Heimerdinger's Hair", "How does it stay up?"), connection);
        Comment.addComment(new Comment(1, 17, null, "Magic, obviously"), connection);

        Post.addPost(new Post(3, 1, 8, 1, "Ekko's Time Travel", "How does it work?"), connection);
        Comment.addComment(new Comment(1, 18, null, "It's all about the clock"), connection);
        Comment.addComment(new Comment(1, 18, null, "Science fiction at its best"), connection);

        Post.addPost(new Post(3, 1, 9, 1, "Mel's Secret", "What's she hiding?"), connection);
        Comment.addComment(new Comment(1, 19, null, "Something big, I'm sure"), connection);

        Post.addPost(new Post(3, 1, 10, 1, "Silco's Eye", "What's the story behind it?"), connection);
        Comment.addComment(new Comment(1, 20, null, "It's a mystery"), connection);

    }



    

    

    public static void printAllComments(List<Comment.CommentLevel> comments) throws SQLException
    {
        for (Comment.CommentLevel commentLevel : comments) 
            {
                System.out.print("#"+commentLevel.comment.id + "\t");
                for (int i = 0; i < commentLevel.level; i++) 
                {
                    System.out.print("  ");
                }
                String username = User.getUserInfo(commentLevel.comment.userID, connection).username;
                String string = username + " > " + commentLevel.comment.content + " : " + commentLevel.comment.vote;
                System.out.println(string);
            }
    }

    

    private static void createTables() throws SQLException 
    {
        String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL UNIQUE," +
                "email TEXT NOT NULL UNIQUE, " +
                "vote INTEGER, " +
                "description TEXT," +
                "password INTEGER NOT NULL, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";

        String createSeriesTable = "CREATE TABLE IF NOT EXISTS series (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL UNIQUE," +
                "description TEXT, " +
                "icon TEXT, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";
        
        String createPostTable = "CREATE TABLE IF NOT EXISTS post (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "seriesID NOT NULL REFERENCES series(id), " +
                "user INTEGER NOT NULL REFERENCES users(id), " +
                "season INTEGER NOT NULL, " +
                "episode INTEGER NOT NULL, " +
                "title TEXT NOT NULL," +
                "content TEXT, " +
                "vote INTEGER, " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";
        
        String createCommentTable = "CREATE TABLE IF NOT EXISTS comment (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "postID INTEGER NOT NULL REFERENCES post(id)," +
                "parentID INTEGER REFERENCES comment(id)," +
                "user INTEGER NOT NULL REFERENCES users(id)," +
                "content TEXT NOT NULL," +
                "vote INTEGER REFERENCES post(id), " +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP)";

        String createUserEpisodeTable = """
        CREATE TABLE IF NOT EXISTS userEpisode (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        userID INTEGER NOT NULL REFERENCES users(id),
        seriesID INTEGER NOT NULL REFERENCES series(id),
        season INTEGER NOT NULL,
        episode INTEGER NOT NULL);""";


        try (Statement stmt = connection.createStatement()) 
        {
            stmt.execute("PRAGMA foreign_keys = ON;");
            stmt.execute(createUserTable);
            stmt.execute(createSeriesTable);
            stmt.execute(createPostTable);
            stmt.execute(createCommentTable);
            stmt.execute(createUserEpisodeTable);
        }
        
    }
}
