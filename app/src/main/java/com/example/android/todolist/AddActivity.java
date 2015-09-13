package com.example.android.todolist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class AddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("to_do_list","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        if (findViewById(R.id.add_activity_fragment_container) != null) {
            if (savedInstanceState==null){
                Log.d("to_do_list","onNewAddFragment");
                AddItemFragment addItemFragment = new AddItemFragment();
                if (getIntent().hasExtra(MainActivity.key)) {
                    addItemFragment.setArguments(getIntent().getExtras());
                }
                getSupportFragmentManager().beginTransaction().add(R.id.add_activity_fragment_container, addItemFragment).commit();
            }
        }
    }
    protected void onPause(){
        Log.d("to_do_list","onPause");
        super.onPause();

    }
    protected void onResume() {
        Log.d("to_do_list","onResume");
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

