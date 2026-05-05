# Distributed Systems Lab

## Requirements

| Tool | Version | Download |
|------|---------|----------|
| Java JDK | **8 only** (Exp 1, 2, 7 use removed APIs) | [jdk.java.net](https://jdk.java.net/java-se-ri/8-MR6) |
| Python | 3.x | [python.org](https://www.python.org/downloads/) |
| g++ with OpenMP | any (MinGW on Windows) | [winlibs.com](https://winlibs.com/) |

> **Why code won't run on your PC:**
> - **Java 11+** removed CORBA (Exp 2) and JAX-WS (Exp 7). Use **Java 8**.
> - **Exp 3** needs g++ with OpenMP — install MinGW-w64, not standard MinGW.
> - **Exp 4** needs 3 separate terminals for clients before server proceeds.

---

## Experiments

### Exp 1 — Java RMI Hello
```
javac Hello.java HelloImpl.java Server.java Client.java
rmiregistry          # terminal 1
java Server          # terminal 2
java Client          # terminal 3
```

### Exp 2 — CORBA Calculator (Java 8 only)
```
idlj -fall Calculator.idl
javac *.java CalculatorModule/*.java
orbd -ORBInitialPort 1050 -ORBInitialHost localhost   # terminal 1
java Server -ORBInitialPort 1050                       # terminal 2
java Client -ORBInitialPort 1050                       # terminal 3
```

### Exp 3 — Distributed Sum (OpenMP / C++)
```
g++ -fopenmp distributed_sum.cpp -o sum.exe
sum.exe
```

### Exp 4 — Clock Synchronization (Python Sockets)
```
python Server.py          # terminal 1  (waits for 3 clients)
python Client.py          # terminal 2
python Client.py          # terminal 3
python Client.py          # terminal 4
```

### Exp 5 — Token Ring (Java)
```
javac TokenRing.java
java TokenRing
```
> Runs infinitely — press `Ctrl+C` to stop.

### Exp 6 — Election Algorithms (Java)
```
javac BullyAlgorithm.java
java BullyAlgorithm

javac RingAlgorithm.java
java RingAlgorithm
```

### Exp 7 — Web Service JAX-WS (Java 8 only)
```
javac -d . CalculatorInterface.java CalculatorService.java CalculatorPublisher.java CalculatorClient.java
java calculator.CalculatorPublisher    # terminal 1
java calculator.CalculatorClient       # terminal 2
```
> WSDL available at: `http://localhost:8080/calculator?wsdl`
