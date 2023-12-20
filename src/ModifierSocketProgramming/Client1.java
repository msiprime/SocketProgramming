package ModifierSocketProgramming;

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
            System.out.println("Enter first number:");
            int num1 = sc.nextInt();
            System.out.println("Enter second number:");
            int num2 = sc.nextInt();
            System.out.println("Enter operator (Sum/Subtract/Multiplication/Division/Modules/ENDS):");
            String operator = sc.next();
            dos.writeInt(num1);
            dos.writeInt(num2);
            dos.writeUTF(operator);
            int result = dis.readInt();
            System.out.println("Result from server: " + result);
            if (operator.equals("ENDS")) {
                System.out.println("Closing the connection" + socket);
                socket.close();
                break;
            }
        }
    }
}