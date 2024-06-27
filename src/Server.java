import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server 
{
    private static int port = 9806;
    public static void startServer()
    {
       System.out.println("Starting Server...");
        ArrayList<ServerThread> threads = new ArrayList<>();
        try( ServerSocket ss = new ServerSocket(port))
        {
            System.out.println("Server Started");
            while(true)
            {
                Socket socket = ss.accept();
                ServerThread serverThread = new ServerThread(socket, socket.getInetAddress());
                threads.add(serverThread);
                System.out.println(socket.getInetAddress()+" Is Connected");
                serverThread.start();
            }
        }
        catch(Exception e) {e.printStackTrace();} 
    }
}
