# EXPERIMENT 5 — Token Ring Mutual Exclusion

## What is this?
In a network where many processes want to access a shared resource (Critical Section), **Token Ring** ensures only ONE process uses it at a time. A special "token" is passed around in a circle — only the process holding the token can enter the critical section.

---

## File: `TokenRing.java`

```java
int token = 0;  // Process 0 starts with the token

do {
    System.out.println("Current token with Process " + token);
    choice = sc.nextInt();   // Ask: do you want to use Critical Section?

    if (choice == 1) {
        System.out.println("Process " + token + " entering Critical Section...");
        Thread.sleep(1000);  // Simulates doing work
        System.out.println("Process " + token + " leaving Critical Section...");
    }
    token = (token + 1) % n;  // Pass token to next process (circular)
} while (true);
```

---

## Key Concepts

| Concept | Explanation |
|---|---|
| `token` | The "permission slip" — whoever has it can enter the critical section |
| `(token + 1) % n` | Passes the token to the next process; wraps around to 0 after the last process |
| Critical Section | The shared resource — only 1 process can be here at a time |
| `Thread.sleep(1000)` | Simulates the process doing some work inside the critical section |

---

## Example with 3 Processes
```
Token at P0 → P0 enters CS → done → Token moves to P1
Token at P1 → P1 skips       → Token moves to P2
Token at P2 → P2 enters CS → done → Token moves back to P0
... (repeats forever)
```
