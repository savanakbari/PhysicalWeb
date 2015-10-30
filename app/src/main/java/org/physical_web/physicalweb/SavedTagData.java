package org.physical_web.physicalweb;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Map;


public class SavedTagData extends Activity {

    ArrayList<String> tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_tag_data);
        SharedPref prefs=new SharedPref(this.getBaseContext());
        Map<String,?> urls=prefs.getAllPrefs();
        if (urls.size()==0)
            return;
        tag=new ArrayList<>();
        for(String key:urls.keySet()){
            tag.add(urls.get(key).toString());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_view_saved_data,tag);
        ListView lv= (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String lvdata= (String) ((TextView) view).getText();
                Intent intent= new Intent(getBaseContext(),TagData.class);
                intent.putExtra("tagdata",lvdata);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_saved_tag_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
