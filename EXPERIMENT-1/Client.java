import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Hello obj = (Hello) Naming.lookup("rmi://localhost/HelloService");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            String response = obj.sayHello(name);
            System.out.println("Server Response: " + response);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}