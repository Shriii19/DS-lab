# EXPERIMENT 4 — Berkeley Clock Synchronization (Python Sockets)

## What is this?
In a distributed system, different computers have different clock times. **Berkeley Algorithm** synchronizes all clocks by:
1. A master asks all clients for their current time.
2. Calculates the **average** time across all nodes.
3. Tells each node how much to adjust its clock.

---

## Files & What They Do

### `Server.py` — The Master (Time Coordinator)
```python
# Step 1: Accept 3 client connections
for i in range(3):
    conn, addr = s.accept()
    clients.append(conn)

# Step 2: Ask each client for their time
for c in clients:
    c.send(b"TIME")

# Step 3: Collect times and compute average
times = []
for c in clients:
    client_time = float(c.recv(1024).decode())
    times.append(client_time)

server_time = time.time()
times.append(server_time)
avg_time = sum(times) / len(times)  # Average of all clocks

# Step 4: Send each client how much to adjust
for i in range(len(clients)):
    adjustment = avg_time - times[i]
    clients[i].send(str(adjustment).encode())
```
- Waits for 3 clients to connect.
- Sends `"TIME"` as a request for each client's current time.
- Adds its own time to the list and calculates the **average**.
- Sends each client the **difference** (how much they need to add or subtract).

---

### `Client.py` — The Slave (Gets Synchronized)
```python
local_time = time.time() + random.randint(-5, 5)  # Simulated wrong clock

s.send(str(local_time).encode())          # Send current time to server

adjustment = float(s.recv(1024).decode()) # Receive correction
local_time += adjustment                  # Apply correction
```
- Simulates a clock that is off by a random amount (-5 to +5 seconds).
- Sends its time to the server.
- Receives the adjustment and corrects its clock.

---

## Flow Summary
```
Server asks "What time is it?"
    → Clients reply with their current times
    → Server calculates average of all times
    → Server tells each client "Add X seconds to your clock"
    → Each client adjusts its clock
```
