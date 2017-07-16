package com.example.prasoon.attendancemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {

    String name;// = getIntent().getExtras().getString("name");
    Button addBunk, subBunk, addTotal, subTotal,uppp, delete;
    TextView subname, bunkdays, workingdays, percentage, minpp;
    String minpercent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        subname = (TextView) findViewById(R.id.subName);
        bunkdays = (TextView) findViewById(R.id.numberofBunks);
        workingdays = (TextView) findViewById(R.id.numberofWorkingDays);
        percentage = (TextView) findViewById(R.id.showPercentage);
        addBunk = (Button) findViewById(R.id.addBunk);
        subBunk = (Button) findViewById(R.id.subBunk);
        addTotal = (Button) findViewById(R.id.addTotal);
        subTotal = (Button) findViewById(R.id.subTotal);
        uppp = (Button) findViewById(R.id.uppp);
        name = getIntent().getExtras().getString("name");
        delete = (Button) findViewById(R.id.delete);
        minpp = (TextView) findViewById(R.id.minpercent);
        //name = "Enter Subject Name";

        final SQLiteDatabase sql = openOrCreateDatabase("subjects", MODE_PRIVATE, null);
        sql.execSQL("create table if not exists sub (name varchar, wdays varchar, bdays varchar, percent varchar, minper varchar)");

        String fetch = "select * from sub where name='"+name+"' ";
        final Cursor n = sql.rawQuery(fetch, null);

        String bdays = "", wdays="", percent="";

        if(n.moveToFirst()) {
            while (!n.isAfterLast()) {
                bdays = n.getString(2);
                wdays = n.getString(1);
                percent = n.getString(3);
                minpercent = n.getString(4);

                n.moveToNext();
            }
        }

        subname.setText(name);
        bunkdays.setText(bdays);workingdays.setText(wdays);
        percentage.setText(percent+" %");minpp.setText(minpercent+" %");
        colorchange();



        addBunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int b = Integer.parseInt(bunkdays.getText().toString()) + 1;
                int newtot = Integer.parseInt(workingdays.getText().toString()) + 1;
                float newper = ((float) (newtot - b) / newtot) * 100;

                int x = (int)(newper*100);
                newper = (float) (x*1.0)/(100);

                bunkdays.setText(b+""); workingdays.setText(newtot+""); percentage.setText(newper+" %");
                colorchange();

            }
        });

        subBunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int b = Integer.parseInt(bunkdays.getText().toString());
                int newtot = Integer.parseInt(workingdays.getText().toString());
                if(b>0) {
                    b--;
                    newtot--;
                }
                float newper;
                if(newtot==0) newper = 0;
                else newper = ((float) (newtot - b) / newtot) * 100;

                int x = (int)(newper*100);
                newper = (float) (x*1.0)/(100);

                bunkdays.setText(b+""); workingdays.setText(newtot+""); percentage.setText(newper+" %");
                colorchange();
            }
        });

        addTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int b = Integer.parseInt(bunkdays.getText().toString());
                int newtot = Integer.parseInt(workingdays.getText().toString()) + 1;
                float newper = ((float) (newtot - b) / newtot) * 100;

                int x = (int)(newper*100);
                newper = (float) (x*1.0)/(100);

                bunkdays.setText(b+""); workingdays.setText(newtot+""); percentage.setText(newper+" %");
                colorchange();
            }
        });

        subTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int b = Integer.parseInt(bunkdays.getText().toString());
                int newtot = Integer.parseInt(workingdays.getText().toString());

                if(newtot==b && b!=0) {
                    b--;
                    newtot--;
                }
                else if(newtot>0)
                {
                    newtot--;
                }

                if(newtot<0) newtot = 0;

                float newper;

                if(newtot==0) newper =0;
                else newper = ((float) (newtot - b) / newtot) * 100;

                int x = (int)(newper*100);
                newper = (float) (x*1.0)/(100);

                bunkdays.setText(b+""); workingdays.setText(newtot+""); percentage.setText(newper+" %");
                colorchange();
            }
        });

        uppp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x = bunkdays.getText().toString();
                String y = workingdays.getText().toString();
                String z = percentage.getText().toString().split(" ")[0];
                sql.execSQL("delete from sub where name='"+name+"'");
                sql.execSQL("insert into sub values ('" + name + "', '" + y + "', '" + x + "', '" + z + "', '" + minpercent + "')");
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sql.execSQL("delete from sub where name='"+name+"'");
                finish();
            }
        });


    }

    public void colorchange()
    {
        double p = Double.parseDouble(percentage.getText().toString().split(" ")[0]);
        double mp = Double.parseDouble(minpp.getText().toString().split(" ")[0]);
        if(p>=mp)
        {
            percentage.setBackgroundColor(Color.GREEN);
        }
        else {
            percentage.setBackgroundColor(Color.RED);
        }
    }
}
