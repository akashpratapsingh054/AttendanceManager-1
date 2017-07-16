package com.example.prasoon.attendancemanager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Subject extends AppCompatActivity {

    private EditText sub;
    private EditText minPer;
    private Button addData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        sub = (EditText) findViewById(R.id.subName);
        minPer = (EditText) findViewById(R.id.minPercentage);
        addData = (Button) findViewById(R.id.addSubject);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDatabse();
            }
        });
    }

    public void addDatabse() {

        String subName = sub.getText().toString();
        String minPercent = minPer.getText().toString();
        if(Double.parseDouble(minPercent)>100) minPercent = "100";
        String zero = "0";

        if(!subName.equals("")) {
            SQLiteDatabase sql = openOrCreateDatabase("subjects", MODE_PRIVATE, null);
            sql.execSQL("create table if not exists sub (name varchar, wdays varchar, bdays varchar, percent varchar, minper varchar)");
            sql.execSQL("insert into sub values ('" + subName + "', '" + zero + "', '" + zero + "', '" + zero + "', '" + minPercent + "')");
            finish();
        }
    }
}
