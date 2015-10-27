import socket
import mysql.connector
from mysql.connector import connect


def connecttodb():
    return connect(user='root', password='root', host='127.0.0.1', database='physical_web')


def handlerequest(clientsocket: socket.socket):
    conn = connecttodb()
    cursor = conn.cursor()
    tagid = bytes.decode(clientsocket.recv(3))
    query = 'select * from tagdata where id={0}'.format(tagid)
    cursor.execute(query)
    s=[]
    for (id, location, officehours, otherinfo, description) in cursor:
        s = [str(id)+','+location + ',', officehours + ',', otherinfo + ',', description]
        s.insert(0, str(len(''.join(s)))+',')
    clientsocket.send(bytes(''.join(s),encoding='utf8'))
    clientsocket.close()