import socket

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('localhost', 8888))
s.send(b'001')
numbytes = int(bytes.decode(s.recv(1)))
location = bytes.decode(s.recv(numbytes))
numbytes = int(bytes.decode(s.recv(2)))
officehrs = bytes.decode(s.recv(numbytes))
numbytes = int(bytes.decode(s.recv(2)))
otherinfo = bytes.decode(s.recv(numbytes))
numbytes = int(bytes.decode(s.recv(2)))
desc = bytes.decode(s.recv(numbytes))
s.close()
print(location)
print(officehrs)
print(otherinfo)
print(desc)