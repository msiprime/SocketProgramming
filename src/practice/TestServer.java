package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class TestServer {
    public static void main(String[] args) throws IOException {
        ServerSocket handshake = new ServerSocket(3400);
        System.out.println("port created, waiting for client: ");

        while (true) {
            Socket clientSocket = handshake.accept();
            System.out.println("Client accepted at : " + clientSocket.getPort());
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            ///////////////////////////////////////////////////////////////////////////
            ClientHandler client = new ClientHandler(dis, dos, clientSocket);
            Thread thread = new Thread(client);
            thread.start();
        }

    }
}

class ClientHandler extends Thread {
    DataOutputStream dos;
    DataInputStream dis;
    Socket socket;

    public ClientHandler(DataInputStream dis, DataOutputStream dos, Socket socket) {
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Client Says: " + dis.readUTF());
                String text = sc.nextLine();
                dos.writeUTF(text);
                if (text.equals("BYE")) {
                    this.socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
