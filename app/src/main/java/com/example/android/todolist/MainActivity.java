package com.example.android.todolist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void onStart(){
        super.onStart();
        new LoadPrefFile().execute(this);
    }
    protected void onResume(){
        super.onResume();
    }
    protected void onRestart(){
        super.onRestart();
    }
    protected void onPause(){
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_add:
                startAddActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    
    }
    private class LoadPrefFile extends AsyncTask<Context,Void,CustomAdapter> {
        ProgressDialog dialog;
        protected void onPreExecute(){
            dialog=new ProgressDialog(MainActivity.this);
            dialog.setMessage(getResources().getString(R.string.loading));
            dialog.show();
        }
        protected CustomAdapter doInBackground(Context... contexts){
            TripleSharedPrefs tsp=new TripleSharedPrefs(contexts[0].getApplicationContext(),"Main_Activity");
            ArrayList<Reminder> reminderArrayList=tsp.getAll();
            customAdapter = new CustomAdapter(MainActivity.this, reminderArrayList);
            return customAdapter;
        }
        protected void onPostExecute(CustomAdapter result){
            dialog.dismiss();
            ListView listView=(ListView) findViewById(R.id.list_view1);
            listView.setAdapter(customAdapter);
        }
    }
    private void startAddActivity(){
        Intent intent=new Intent(this,AddActivity.class);
        startActivity(intent);
    }
}

