package org.physical_web.physicalweb;


import android.os.AsyncTask;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class BgThread extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] params) {
        return connect();
    }

    private String[] connect(){
        Socket socket=null;
        byte[] databytes=null;
        try {

           // String id=TagData.url_id;
            // replace 001 with id . N0t checked yet
            InetAddress servaddress=InetAddress.getByName("192.168.173.1");
            socket=new Socket();
            socket.setReuseAddress(true);
            socket.connect(new InetSocketAddress(servaddress, 8888));
            OutputStream os=socket.getOutputStream();
            os.write("001".getBytes());
            socket.shutdownOutput();
            InputStream is=socket.getInputStream();
            String s="";
            while(true){
                char c=(char)is.read();
                if(c!=','){
                    s+=c;
                }
                else{
                    break;
                }
            }
            int datalength=Integer.parseInt(s);
            databytes=new byte[datalength];
            is.read(databytes);
        } catch (SocketException e1) {
            e1.printStackTrace();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        finally {
            if(socket!=null && socket.isConnected())
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return databytes!=null?new String(databytes).split(","):null;
    }
}

