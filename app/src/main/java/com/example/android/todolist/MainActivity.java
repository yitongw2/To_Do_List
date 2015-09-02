package com.example.android.todolist;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reminder[] reminders=new Reminder[4];
        SharedPreferences sharedPref=this.getSharedPreferences("Main_Preference",Context.MODE_PRIVATE);
        ArrayList<Reminder> new_reminder_arraylist=new ArrayList<Reminder>();
        Map<String,?> map=sharedPref.getAll();
        for (Map.Entry<String,?> entry:map.entrySet()){
            String temp_s= (String) entry.getValue();
            String[] temp=temp_s.split("-");
            new_reminder_arraylist.add(new Reminder(temp[1],temp[0],temp[2]));
        }
        CustomAdapter c_adapter=new CustomAdapter(this,new_reminder_arraylist);
        ListView listView = (ListView) findViewById(R.id.list_view1);
        listView.setAdapter(c_adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement



        return super.onOptionsItemSelected(item);
    }
}
