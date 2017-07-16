package com.example.prasoon.attendancemanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.mainLayout);

        Log.e("hh", "in listdata7");
        listData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("hh", "hello");
                Intent i = new Intent(MainActivity.this, Subject.class);
                startActivity(i);
                Log.e("hh", "hellooooowwww");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView n =  (TextView) view.findViewById(R.id.subName);
                String name = n.getText().toString();
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listData();
    }

    private void listData()
    {
        Log.e("hh", "in listdata");
        SQLiteDatabase sql = openOrCreateDatabase("subjects", MODE_PRIVATE, null);
        sql.execSQL("create table if not exists sub (name varchar, " +
                "wdays varchar, bdays varchar, percent varchar, minper varchar)");

        String fetch = "select * from sub";

        Log.e("hh", "in listdata1");
        Cursor cursor = sql.rawQuery(fetch, null);

        ArrayList<SubjectData> array = new ArrayList<>();

        Log.e("hh", "in listdata2");
        if(cursor.moveToFirst())
        while (!cursor.isAfterLast()) {

            Log.e("hh", "in listdata3");
            SubjectData newSub = new SubjectData(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            array.add(newSub);

            cursor.moveToNext();
        }

        Log.e("hh", "in listdata4");

        if(array.size()>0) {
            Collections.sort(array, new Comparator<SubjectData>() {
                @Override
                public int compare(SubjectData subjectData, SubjectData t1) {
                    return subjectData.getName().compareTo(t1.getName());
                }
            });
            Adaptor adaptor = new Adaptor(MainActivity.this, array);
            listView.setAdapter(adaptor);
        }
        Log.e("hh", "in listdata5");


    }
}
