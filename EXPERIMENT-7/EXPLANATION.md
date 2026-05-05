# EXPERIMENT 7 — Web Services (JAX-WS SOAP)

## What is this?
A **Web Service** is a way for applications to communicate over the internet using standard HTTP. JAX-WS creates **SOAP-based web services** — the server publishes a service, and the client calls it like a normal method. The WSDL file (auto-generated) acts as the service's "manual."

---

## Files & What They Do

### `CalculatorInterface.java` — The Contract
```java
@WebService
public interface CalculatorInterface {
    @WebMethod
    int add(int a, int b);
}
```
- `@WebService` marks this as a web service interface.
- `@WebMethod` marks `add()` as a method that clients can call remotely over HTTP.

---

### `CalculatorService.java` — The Implementation
```java
@WebService(endpointInterface = "calculator.CalculatorInterface")
public class CalculatorService implements CalculatorInterface {
    public int add(int a, int b) {
        return a + b;
    }
}
```
- The actual logic: just adds two numbers.
- `endpointInterface` links it to the interface above.

---

### `CalculatorPublisher.java` — Starts the Server
```java
Endpoint.publish("http://localhost:8080/calculator", new CalculatorService());
System.out.println("http://localhost:8080/calculator?wsdl");
```
- **Publishes** the web service at `http://localhost:8080/calculator`.
- Anyone can visit `?wsdl` to see the service description (like a menu of what's available).
- `Endpoint.publish()` = "Start the service and make it accessible via HTTP."

---

### `CalculatorClient.java` — The Client
```java
URL url = new URL("http://localhost:8080/calculator?wsdl");
QName qname = new QName("http://calculator/", "CalculatorService");
Service service = Service.create(url, qname);
CalculatorInterface calc = service.getPort(CalculatorInterface.class);

int result = calc.add(10, 20);  // Calls the remote method
```
- Downloads the WSDL to understand what the service offers.
- Gets a **proxy object** (`calc`) that looks like a local object but actually calls the server.
- Calls `add(10, 20)` — this sends an HTTP request to the server, which returns 30.

---

## Flow Summary
```
Client reads WSDL
    → Creates a proxy object
    → Calls add(10, 20)
    → HTTP/SOAP request sent to server
    → Server runs add() and returns 30
    → HTTP/SOAP response received
    → Client prints "Result = 30"
```
