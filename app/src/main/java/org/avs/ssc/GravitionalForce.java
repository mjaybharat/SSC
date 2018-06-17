package org.avs.ssc;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class GravitionalForce extends AppCompatActivity {
    private ArrayList<String> Name = new ArrayList<>();
    private ArrayList<String> Age = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravitional_force);

        RelativeLayout rl=findViewById(R.id.rl);
        ScrollView sv = new ScrollView(this);
        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);

        sv.addView(ll);

        SQLiteDatabase myDB = openOrCreateDatabase("my.db", MODE_PRIVATE, null);


        //myDB.execSQL("CREATE TABLE IF NOT EXISTS user (name VARCHAR(200), age INT, is_single INT)");
        //ContentValues row1 = new ContentValues();
        //row1.put("name", "Alice");
        //row1.put("age", 25);
        //row1.put("is_single", 1);

        //ContentValues row2 = new ContentValues();
        //row2.put("name", "Bob");
        //row2.put("age", 20);
        //row2.put("is_single", 0);
       // myDB.insert("user", null, row1);
       // myDB.insert("user", null, row2);

        Cursor myCursor =  myDB.rawQuery("select name, age from user", null);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(GravitionalForce.this);
        if (myCursor.moveToFirst()) {

            do {

                Name.add(myCursor.getString(myCursor.getColumnIndex("name")));
                Age.add(myCursor.getString(myCursor.getColumnIndex("age")));
                Button b = new Button(this);
                b.setText(String.format("%s%s", getString(R.string.Button), myCursor.getString(myCursor.getColumnIndex("name"))));
                dlgAlert.setMessage(myCursor.getString(myCursor.getColumnIndex("name")));
                dlgAlert.setTitle("App Title");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                ll.addView(b);

            }            while (myCursor.moveToNext());
        }

        rl.addView(sv);
        myCursor.close();
        myDB.close();



        dlgAlert.setMessage("Load Successful!");
        dlgAlert.setTitle("App Title");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();







    }





}
