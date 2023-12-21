package practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestClient1 {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 3400);
        System.out.println("Client connected at: " + clientSocket.getPort());
        DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = scanner.nextLine();
            dos.writeUTF(text);
            System.out.println("Server says: " + dis.readUTF());


        }

    }
}
