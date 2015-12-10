package org.physical_web.physicalweb.ssdp;

import android.os.AsyncTask;

import org.physical_web.physicalweb.TagData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Savan on 12/9/2015.
 */
public class BgThreadPreview extends AsyncTask {
    String key;
    public BgThreadPreview(String key){
        this.key=key;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        return connect();
    }

    private String connect(){
        Socket socket=null;
        byte[] databytes=null;
        try {

            String id= this.key;
            // replace 001 with id . N0t checked yet
            InetAddress servaddress=InetAddress.getByName("192.168.173.1");
            socket=new Socket();
            socket.setReuseAddress(true);
            socket.connect(new InetSocketAddress(servaddress, 8888));
            OutputStream os=socket.getOutputStream();
            os.write(id.getBytes());  // os.write("001".getBytes();
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
        return databytes!=null?new String(databytes).split(",")[1]:null;
    }
}
