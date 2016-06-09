package com.example.dominika.appobchod;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beata on 09.05.2016.
 */
public class AllergensListViewItemAdapter extends ArrayAdapter<String> {
    private int layout;
    List <String> data = null;
    Context context;
    List <String> choosen_allergin = new ArrayList<String>();;

    public AllergensListViewItemAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        layout = resource;
        data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RowHolder myRowHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(layout,parent,false);
            RowHolder rowHolder = new RowHolder();
            rowHolder.Allergin = (CheckBox) row.findViewById(R.id.AllerginCheckBox);
            rowHolder.Allergin.setText(data.get(position));
            row.setTag(rowHolder);
        }
        else{
            myRowHolder = (RowHolder) row.getTag();
            myRowHolder.Allergin.setText(getItem(position));
        }
        return row;
        //return super.getView(position, convertView, parent);
    }

    public List<String> choosen() {
        return choosen_allergin;
    }

    public class RowHolder{
        CheckBox Allergin;
    }
}
