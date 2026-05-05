import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    public HelloImpl() throws RemoteException {
        super();
    }

    // This method will be called by multiple clients (multi-threaded)
    public String sayHello(String name) throws RemoteException {
        System.out.println("Client request handled by thread: " + Thread.currentThread().getName());
        return "Hello " + name + " from Server!";
    }
}