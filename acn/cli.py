import socket

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('localhost', 8888))
s.send(b'001')
numbytes = ''
while True:
    b = bytes.decode(s.recv(1))
    if b != ',':
        numbytes += b
    else:
        break
numbytes = int(numbytes)
data = bytes.decode(s.recv(numbytes)).split(',')
for d in data:
    print(d)
