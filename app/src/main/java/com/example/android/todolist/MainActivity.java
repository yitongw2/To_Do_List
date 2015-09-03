package com.example.android.todolist;



import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private CustomAdapter customAdapter;
    private TripleSharedPrefs tsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TripleSharedPrefs tsp=new TripleSharedPrefs(this,"MainActivity");
        new LoadPrefFile().execute(tsp);
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
    private class LoadPrefFile extends AsyncTask<TripleSharedPrefs,Void,CustomAdapter>{
        protected CustomAdapter doInBackground(TripleSharedPrefs... tsps){
            ArrayList<Reminder> reminderArrayList=tsps[0].getAll();
            customAdapter=new CustomAdapter(tsps[0].getContext(),reminderArrayList);
            return customAdapter;
        }
        protected void onPostExecute(CustomAdapter result){
            ListView listView=(ListView) findViewById(R.id.list_view1);
            listView.setAdapter(result);
        }
    }

}
