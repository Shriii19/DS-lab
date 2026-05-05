# EXPERIMENT 2 — CORBA (Common Object Request Broker Architecture) — Calculator

## What is this?
CORBA is an older technology (like RMI but language-independent) that allows objects on different machines and even **different programming languages** to talk to each other. A middleman called the **ORB (Object Request Broker)** handles all the communication.

---

## Files & What They Do

### `Calculator.idl` — The Blueprint (Interface Definition Language)
```
interface Calculator {
    long add(in long a, in long b);
    long sub(in long a, in long b);
    long mul(in long a, in long b);
    float div(in long a, in long b);
};
```
- `.idl` is a **neutral language** (not Java, not C++) that describes what the service offers.
- It says: "I have 4 operations — add, subtract, multiply, divide."
- This file is compiled to auto-generate Java code (stubs & skeletons).

---

### `CalculatorImpl.java` — The Actual Math Logic
```java
public int add(int a, int b) { return a + b; }
public float div(int a, int b) { if (b == 0) return 0; return (float) a / b; }
```
- Implements all 4 operations.
- Note the **division check** — if `b` is 0, it returns 0 instead of crashing.
- Extends `CalculatorPOA` which was auto-generated from the `.idl` file.

---

### `Server.java` — The CORBA Server
```java
ORB orb = ORB.init(args, null);           // Start the ORB (the middleman)
rootpoa.the_POAManager().activate();       // Activate the object manager
ncRef.rebind(path, href);                  // Register "Calculator" in the Name Service
orb.run();                                 // Keep server running
```
- Starts the ORB (the communication engine).
- Registers the `CalculatorImpl` in a **Name Service** (like a phone book for services).
- Runs forever, waiting for client requests.

---

### `Client.java` — The CORBA Client
```java
Calculator calc = CalculatorHelper.narrow(ncRef.resolve_str("Calculator"));
System.out.println("Addition: " + calc.add(a, b));
```
- Looks up `"Calculator"` in the Name Service.
- Calls operations as if they were local — CORBA handles the remote call transparently.
- Prints all 4 results.

---

## Flow Summary
```
Client → ORB → Name Service (find "Calculator") → Server's CalculatorImpl → Result back to Client
```
