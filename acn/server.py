import socket
import threading
from threading import Thread
import traceback
from acn import requesthandler


def getserversocket():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    s.bind(('', 8888))
    s.listen(10)
    return s

s = getserversocket()
try:
    while True:
        print('Waiting for request...')
        clientsocket, clientaddress = s.accept()
        clientsocket.settimeout(None)
        handlerthread = Thread(target=requesthandler.handlerequest,args=[clientsocket])
        handlerthread.start()
except:
    traceback.print_exc()