package vanilla_code;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 3440);
        System.out.println("connected");
        Scanner sc = new Scanner(System.in);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        while (true) {
            System.out.println(dis.readUTF());
            String toSend = sc.nextLine();
            dos.writeUTF(toSend);

            if (toSend.equals("Bye")) {
                System.out.println("Closing the connection" + socket);
                socket.close();
                break;
            }

            String received = dis.readUTF();
            System.out.println(received);
        }
    }
}