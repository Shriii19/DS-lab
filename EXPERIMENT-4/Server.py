import socket
import time

clients = []
HOST = '127.0.0.1'
PORT = 5000

s = socket.socket()
s.bind((HOST, PORT))
s.listen(5)
print("Master Server Started...")

for i in range(3):  # Accept 3 clients
    conn, addr = s.accept()
    print("Connected with", addr)
    clients.append(conn)

# Request time from clients
for c in clients:
    c.send(b"TIME")

times = []
for c in clients:
    client_time = float(c.recv(1024).decode())
    print("Client time:", client_time)
    times.append(client_time)

server_time = time.time()
times.append(server_time)
avg_time = sum(times) / len(times)
print("Average Time:", avg_time)

# Send adjustments
for i in range(len(clients)):
    adjustment = avg_time - times[i]
    clients[i].send(str(adjustment).encode())

print("Synchronization Done")
s.close()