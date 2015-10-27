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

        textViewData = new TextView[textView.length];
        for(int i=0; i< textViewData.length ;i++){
            textViewData[i]= (TextView) findViewById(textView[i]);
        }
    }

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
