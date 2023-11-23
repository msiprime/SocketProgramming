import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.*;
import java.util.*;

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

        Thread t = new ClientHandler(s, dis, dos);
        t.start();
    }
}

class ClientHandler extends Thread {
    final Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dos = dos;
        this.dis = dis;
    }

    @Override
    public void run() {
        DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    }
}