import java.io.IOException;
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

class Client2 {

}

class Client3 {

}

class Server {

}