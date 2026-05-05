# EXPERIMENT 1 — Java RMI (Remote Method Invocation) — Hello World

## What is this?
RMI lets a program on one computer call a method (function) that actually runs on **another computer** over the network. Think of it like calling a friend and asking them to do a task for you — you ask, they do it, and send back the result.

---

## Files & What They Do

### `Hello.java` — The Contract (Interface)
```java
public interface Hello extends Remote {
    String sayHello(String name) throws RemoteException;
}
```
- This is just a **promise/contract** that says: "There will be a method called `sayHello` that takes a name and returns a greeting."
- `Remote` means this method can be called from another machine.
- `RemoteException` is thrown if something goes wrong over the network.

---

### `HelloImpl.java` — The Actual Worker (Server-side Logic)
```java
public class HelloImpl extends UnicastRemoteObject implements Hello {
    public String sayHello(String name) throws RemoteException {
        return "Hello " + name + " from Server!";
    }
}
```
- This **actually does the work** — it implements the `sayHello` method.
- `UnicastRemoteObject` makes this object available over the network.
- When a client calls `sayHello("Alice")`, this returns `"Hello Alice from Server!"`.

---

### `Server.java` — The Server
```java
HelloImpl obj = new HelloImpl();
Naming.rebind("rmi://localhost/HelloService", obj);
```
- Creates the `HelloImpl` object.
- **Registers** it on the network under the name `"HelloService"` so clients can find it.
- `Naming.rebind` = "Put this service on the shelf with this label."

---

### `Client.java` — The Client
```java
Hello obj = (Hello) Naming.lookup("rmi://localhost/HelloService");
String response = obj.sayHello(name);
```
- **Looks up** the service by name over the network.
- Calls `sayHello()` — even though this method physically runs on the server, it feels like a normal local call.
- Prints the response from the server.

---

## Flow Summary
```
Client types name
    → Client calls sayHello()
    → Network
    → Server runs sayHello()
    → Returns "Hello [name] from Server!"
    → Client prints it
```
