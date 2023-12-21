package ModifierSocketProgramming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;


public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket handshake = new ServerSocket(3440);
        System.out.println("Server connected at " + handshake.getLocalPort());
        System.out.println("Server is connecting\n");
        System.out.println("Wait for the client\n");

        for (int i = 0; i < 5; i++) {
            Socket com_socket = handshake.accept();
            System.out.println("A new client is connected " + com_socket);
            DataOutputStream dos = new DataOutputStream(com_socket.getOutputStream());
            DataInputStream dis = new DataInputStream(com_socket.getInputStream());
            System.out.println("A new thread is assigning");
            Thread new_tunnel = new ClientHandler(com_socket, dis, dos);
            new_tunnel.start();
        }
        handshake.close();
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
        while (true) {
            try {
                int num1 = input.readInt();
                int num2 = input.readInt();
                String operator = input.readUTF();
                int result;
                switch (operator) {
                    case "Sum":
                        result = num1 + num2;
                        break;
                    case "Subtract":
                        result = num1 - num2;
                        break;
                    case "Multiplication":
                        result = num1 * num2;
                        break;
                    case "Division":
                        result = num1 / num2;
                        break;
                    case "Modules":
                        result = num1 % num2;
                        break;
                    default:
                        result = 0;
                }
                output.writeInt(result);
                if (operator.equals("ENDS")) {
                    System.out.println("Client " + this.soc + " requested to end the connection.");
                    this.soc.close();
                    System.out.println("Connection closed");
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