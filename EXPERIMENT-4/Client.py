import socket
import time
import random

HOST = '127.0.0.1'
PORT = 5000

s = socket.socket()
s.connect((HOST, PORT))

# Simulated clock (random offset)
local_time = time.time() + random.randint(-5, 5)

msg = s.recv(1024).decode()

if msg == "TIME":
    print("Sending time:", local_time)
    s.send(str(local_time).encode())

# Receive adjustment
adjustment = float(s.recv(1024).decode())

# Adjust clock
local_time += adjustment
print("Adjusted Time:", local_time)

s.close()