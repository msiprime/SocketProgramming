import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {

    }
}


class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 3440);
        System.out.println("connected");
    }
}

class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3440);
        System.out.println("Waiting for client");

        Socket s = ss.accept();
        System.out.println("A new client is connected");

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
    }
}