package org.physical_web.physicalweb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.physical_web.physicalweb.R;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TagData extends Activity {

    TextView tagid, info, location, officehour, description;
    TextView tagid_data,info_data,location_data,officehour_data,description_data;
    public static String recv_data;
    public static String url_id;
    SharedPreferences sharedPref;
    public String PHYDB="phydb_pref";


//   List<String> resultTag = Arrays.asList("001", "ERB", "Dr elmasri");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_data);


     tagid_data = (TextView)findViewById(R.id.tagId_data);
     location_data = (TextView)findViewById(R.id.location_data);
     officehour_data = (TextView)findViewById(R.id.officeHour_data);
     info_data = (TextView)findViewById(R.id.info_data);
     description_data = (TextView)findViewById(R.id.description_data);

    recv_data=getIntent().getStringExtra("tagdata");
    int length =recv_data.length();
    int index=recv_data.lastIndexOf('/');
    url_id =recv_data.substring(index +1, length);
    //tagid_data.setText(url_id);


  //  sharedPref= getSharedPreferences(PHYDB, Context.MODE_PRIVATE);
    //    sharedPref.edit().putStringSet(url_id,resultTag);

      //  SharedPref pref =new SharedPref(this);
    //pref.putListString("001",resultTag);

    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    BlockingQueue<Runnable> queue=new LinkedBlockingQueue<>(maximumPoolSize);
    AsyncTask task=new BgThread().executeOnExecutor(new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,queue));

        try {
          String[] result=(String[])task.get();

            if(result!=null) {
                tagid_data.setText(result[0]);
                location_data.setText(result[1]);
                officehour_data.setText(result[2]);
                info_data.setText(result[3]);
                description_data.setText(result[4]);
            }
          }
         catch (InterruptedException e) {
            e.printStackTrace();
            }
         catch (ExecutionException e) {
            e.printStackTrace();
        }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tag_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

               if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
