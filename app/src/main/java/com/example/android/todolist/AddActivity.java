package com.example.android.todolist;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    String time;
    String location;
    String content;
    int id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }
    protected void onPause(){
        super.onPause();
        EditText editText_time=(EditText) findViewById(R.id.activity_add_time);
        EditText editText_location=(EditText) findViewById(R.id.activity_add_location);
        EditText editText_content=(EditText) findViewById(R.id.activity_add_content);
        time=editText_time.getText().toString();
        location=editText_location.getText().toString();
        content=editText_content.getText().toString();
        new WriteToTPF().execute();
    }
    protected void onStop(){
        super.onStop();
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
    private class WriteToTPF extends AsyncTask<Void,Void,Void>{
        protected Void doInBackground(Void... voids){
            TripleSharedPrefs tsf = new TripleSharedPrefs(AddActivity.this.getApplicationContext(), "Main_Activity");
            String[] s={content,time,location};
            if (id>=0 && content.isEmpty() && time.isEmpty() && location.isEmpty()){
                tsf.remove(Integer.toString(id));
            }
            else if (id>=0 && !content.isEmpty() && !time.isEmpty() && !location.isEmpty()) {
                tsf.edit(Integer.toString(id),s);
            }
            else if (id<0 && !content.isEmpty() && !time.isEmpty() && !location.isEmpty())
                id=tsf.add(s);
            return null;
        }

    }
}

