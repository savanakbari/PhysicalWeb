import socket
import mysql.connector
from mysql.connector import connect


def connecttodb():
    return connect(user='root', password='root', host='127.0.0.1', database='physical_web')


def handlerequest(clientsocket:socket.socket):
    conn = connecttodb()
    cursor = conn.cursor()
    tagid = bytes.decode(clientsocket.recv(3))
    query = 'select * from tagdata where id={0}'.format(tagid)
    cursor.execute(query)
    for (id, location, officehours, otherinfo, description) in cursor:
        s = [str(len(location)), location, str(len(officehours)), officehours, str(len(otherinfo)), otherinfo, str(len(description)),
             description]
        s = ''.join(s)
    clientsocket.send(bytes(s,encoding='utf8'))
    clientsocket.close()