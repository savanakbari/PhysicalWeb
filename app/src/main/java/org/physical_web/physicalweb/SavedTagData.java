package org.physical_web.physicalweb;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.physical_web.physicalweb.ssdp.BgThreadPreview;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class SavedTagData extends Activity {

    ArrayList<String> tag;
    CustomTagAdapter adapter;
    public static ArrayList<String> roomlist;
    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    BlockingQueue<Runnable> queue=new LinkedBlockingQueue<>(maximumPoolSize);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_tag_data);
        SharedPref prefs=new SharedPref(this.getBaseContext());
        Map<String,?> urls=prefs.getAllPrefs();
        if (urls.size()==0)
            return;
        tag=new ArrayList<>();
        roomlist=new ArrayList<>();
        for(String key:urls.keySet()){
            tag.add(urls.get(key).toString());
            getClassInfo(key);
        }
        adapter = new CustomTagAdapter(this,tag);
      //  adapter = new ArrayAdapter<String>(this,R.layout.list_view_saved_data,tag);
        ListView lv= (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String lvdata= (String) ((TextView) view.findViewById(R.id.saved_tag)).getText();
                Intent intent= new Intent(getBaseContext(),TagData.class);
                intent.putExtra("tagdata",lvdata);
                startActivity(intent);
            }
        });


    }

    private void getClassInfo(String key){
        AsyncTask task=new BgThreadPreview(key).executeOnExecutor(new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,queue));

        try {
            String result=(String)task.get();

            if(result!=null) {
                this.roomlist.add(result);
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

        getMenuInflater().inflate(R.menu.menu_saved_tag_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cleardata) {
           SharedPreferences preferences=getSharedPreferences("urlprefs", Context.MODE_PRIVATE);
            if(preferences==null || adapter==null) return true;
            SharedPreferences.Editor editor=preferences.edit();
            editor.clear();
            editor.commit();
            adapter.notifyDataSetChanged();
            startActivity(new Intent(this,SavedTagData.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        startActivity(new Intent(this,MainActivity.class));

    }
}
