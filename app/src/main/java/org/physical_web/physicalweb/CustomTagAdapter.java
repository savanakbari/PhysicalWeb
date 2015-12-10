package org.physical_web.physicalweb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Savan on 12/9/2015.
 */
public class CustomTagAdapter extends ArrayAdapter<String> {

    CustomTagAdapter(Context context,ArrayList<String> tag)
    {
        super(context,R.layout.list_view_saved_data,tag);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView = inflator.inflate(R.layout.list_view_saved_data, parent, false);

        String tagArray = getItem(position);
        TextView txtTag     =  (TextView) customView.findViewById(R.id.saved_tag);
        TextView txtTagTime =  (TextView) customView.findViewById(R.id.time);
        TextView txtClassRoom =  (TextView) customView.findViewById(R.id.classRoom);
        txtTag.setText(tagArray);
        if(position<NearbyBeaconsFragment.timeArray.size()) {
            String date = NearbyBeaconsFragment.timeArray.get(position);
            txtTagTime.setText(date);
        }

        //Toast.makeText(getContext(),date,Toast.LENGTH_LONG).show();
        if(SavedTagData.roomlist!=null && position<SavedTagData.roomlist.size()) {
            String classRoom = SavedTagData.roomlist.get(position);
            txtClassRoom.setText(classRoom);
        }
        return customView;
    }
}
