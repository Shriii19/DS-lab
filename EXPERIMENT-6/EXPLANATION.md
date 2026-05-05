# EXPERIMENT 6 — Leader Election Algorithms

## What is this?
In a distributed system, sometimes the current leader/coordinator crashes. The remaining processes need to **elect a new leader**. This experiment implements two classic election algorithms.

---

## Part A: Bully Algorithm (`BullyAlgorithm.java`)

**Idea:** The process with the **highest ID** wins (bullies the smaller ones). When a process notices the leader is down, it starts an election by challenging all processes with higher IDs.

```java
static void bullyElection(int init) {
    int leader = init;
    for (int i = init + 1; i < n; i++) {
        if (active[i]) {
            System.out.println("Process " + processes[init] + " sends ELECTION to Process " + processes[i]);
            leader = i;  // Highest active process becomes candidate
        }
    }
    if (leader != init) {
        bullyElection(leader);  // Highest process continues the election
    } else {
        System.out.println("Process " + processes[leader] + " becomes LEADER");
    }
}
```

### Step-by-step:
1. A process starts an election and sends `ELECTION` to all processes with **higher IDs**.
2. If a higher process is alive, it takes over and repeats.
3. The process with no higher active process **declares itself leader**.

### Example
Processes: 1, 2, 3, 4 — Process 3 crashed, Process 1 starts election:
```
P1 → sends ELECTION to P2, P4
P2 → sends ELECTION to P4
P4 → no higher process → P4 becomes LEADER
```

---

## Part B: Ring Algorithm (`RingAlgorithm.java`)

**Idea:** All processes are arranged in a **circle**. The election message travels around the ring, collecting process IDs. The highest ID seen wins.

```java
List<Integer> election = new ArrayList<>();
int i = init;

do {
    if (active[i]) {
        election.add(processes[i]);  // Active process adds its ID to the message
        System.out.println("Process " + processes[i] + " passes message");
    }
    i = (i + 1) % n;  // Move to next in ring
} while (i != init);  // Stop when we reach the start again

int leader = Collections.max(election);  // Highest ID wins
```

### Step-by-step:
1. Initiator sends an election message around the ring.
2. Each active process **adds its ID** to the message and forwards it.
3. When the message returns to the initiator, it picks the **maximum ID** as the leader.

### Example
Processes: 1, 2, 3, 4 — Process 2 crashed, Process 1 starts:
```
Message travels: P1 → (P2 skipped, crashed) → P3 → P4 → back to P1
Election list: [1, 3, 4]
Leader = max(1, 3, 4) = 4 → P4 becomes LEADER
```

---

## Comparison

| Feature | Bully Algorithm | Ring Algorithm |
|---|---|---|
| Winner | Highest ID | Highest ID |
| Message path | Direct challenges | Circular ring |
| Crashed nodes | Skipped by checking `active[]` | Skipped in the loop |
