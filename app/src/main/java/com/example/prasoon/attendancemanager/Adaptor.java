package com.example.prasoon.attendancemanager;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adaptor extends ArrayAdapter<SubjectData> {

    public Adaptor(Context context, List<SubjectData> list)
    {
        super(context, 0, list);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View list = convertView;
        if(list==null) {
            list = LayoutInflater.from(getContext()).inflate(R.layout.custom_row, parent, false);
        }

        SubjectData s = getItem(position);
        final String name = s.getName();
        final String bdays = s.getBdays();
        final String percent = s.getPercent();
        final String wdays = s.getWdays();
        final String minp = s.getMinper();

        TextView na = (TextView) list.findViewById(R.id.subName);
        TextView bd = (TextView) list.findViewById(R.id.bunkdays);
        TextView wd = (TextView) list.findViewById(R.id.workingdays);
        TextView per = (TextView) list.findViewById(R.id.percent);
        TextView minper = (TextView) list.findViewById(R.id.minpercent);

        na.setText(name);
        bd.setText("Bunks : "+bdays);
        per.setText(percent+" %");
        wd.setText("Total : "+wdays);
        minper.setText(minp+" %");

        double p = Double.parseDouble(percent.split(" ")[0]);
        double mp = Double.parseDouble(minp.split(" ")[0]);
        if(p>=mp)
        {
            per.setTextColor(Color.GREEN);
        }
        else per.setTextColor(Color.RED);


        return list;
    }
}
