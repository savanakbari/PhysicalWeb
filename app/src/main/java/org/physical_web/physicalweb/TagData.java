package org.physical_web.physicalweb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.physical_web.physicalweb.R;

public class TagData extends ActionBarActivity {


    private  int[] textView = {

            R.id.tagId,R.id.tagId_data,
            R.id.location,R.id.location_data,
            R.id.officeHour,R.id.officeHour_data,
            R.id.info,R.id.info_data,
            R.id.description,R.id.description_data
    };
    private TextView[] textViewData;




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
