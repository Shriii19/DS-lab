import java.rmi.Naming;

public class Server {
    public static void main(String[] args) {
        try {
            HelloImpl obj = new HelloImpl();
            Naming.rebind("rmi://localhost/HelloService", obj);
            System.out.println("Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
        }
    }
}