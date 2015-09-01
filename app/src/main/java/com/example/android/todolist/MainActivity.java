package com.example.android.todolist;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reminder[] reminders=new Reminder[4];
        String[] list_item= {"Mon 6/23 - ICS 31 - SSL 270",
        "Tue 6/24 - Do Homework - Home",
                "Wed 6/25 - Swimming - Pool",
                "Sat 6/28 - Watch AntMan - AMC Tustin",
                };
        for (int j=0;j<list_item.length;j++){
            String[] temp=list_item[j].split("-");
            reminders[j]=new Reminder(temp[1],temp[0],temp[2]);
        }
        ArrayList<Reminder> reminders_arraylist=new ArrayList<Reminder>(Arrays.asList(reminders));
        CustomAdapter c_adapter=new CustomAdapter(this,reminders_arraylist);
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


        return super.onOptionsItemSelected(item);
    }
}
