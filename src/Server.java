import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3440);
        System.out.println("Waiting for client");

        Socket s = ss.accept();
        System.out.println("A new client is connected" + s);

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        Thread t = new ClientHandler(s, dis, dos);
        t.start();
    }
}

class ClientHandler extends Thread {

    final Socket soc;
    final DataInputStream input;
    final DataOutputStream output;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.soc = s;
        this.input = dis;
        this.output = dos;
    }

    public void run() {
        DateFormat forDate = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat forTime = new SimpleDateFormat("hh:mm:ss");
        String received;
        String toReturn;
        while (true) {
            try {
                output.writeUTF("What do you want?Date/Time");
                received = input.readUTF();
                if (received.equals("Bye")) {
                    System.out.println("Client " + this.soc + "sends exit.");
                    this.soc.close();
                    System.out.println("Connection closed");
                    break;
                }

                Date date = new Date();
                switch (received) {
                    case "Date":
                        toReturn = forDate.format(date);
                        output.writeUTF(toReturn);
                        break;
                    case "Time":
                        toReturn = forTime.format(date);
                        output.writeUTF(toReturn);
                        break;
                    default:
                        output.writeUTF("Invalid");
                        break;
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            this.output.close();
            this.input.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}