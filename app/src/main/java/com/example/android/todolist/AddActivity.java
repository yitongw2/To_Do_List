package com.example.android.todolist;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText editText_time;
    EditText editText_location;
    EditText editText_content;
    private int id = -1;
    private final String key="id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            id=savedInstanceState.getInt(key);
        }
        setContentView(R.layout.activity_add);
    }

    protected void onPause() {
        super.onPause();
        String time = editText_time.getText().toString();
        String location = editText_location.getText().toString();
        String content = editText_content.getText().toString();
        new WriteToTPF().execute(content,time,location,Integer.toString(id));
        editText_content=null;
        editText_time=null;
        editText_location=null;
    }
    protected void onResume(){
        super.onResume();
        editText_time = (EditText) findViewById(R.id.activity_add_time);
        editText_location = (EditText) findViewById(R.id.activity_add_location);
        editText_content = (EditText) findViewById(R.id.activity_add_content);
        if (getIntent().hasExtra(key)){
            TripleSharedPrefs tsf = new TripleSharedPrefs(AddActivity.this.getApplicationContext(), "Main_Activity");
            Intent intent=getIntent();
            id=intent.getIntExtra(key,-1);
            Reminder re=tsf.get(id);
            editText_content.setText(re.getContent());
            editText_time.setText(re.getTime());
            editText_location.setText(re.getLocation());
        }
    }
    protected void onStop() {
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

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(key,id);
    }
    public void deleteReminder(View view){
        TripleSharedPrefs tsf = new TripleSharedPrefs(AddActivity.this.getApplicationContext(), "Main_Activity");
        tsf.remove(Integer.toString(id));
        finish();
    }
    private class WriteToTPF extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... strings) {
            String content=strings[0];
            String time=strings[1];
            String location=strings[2];
            TripleSharedPrefs tsf = new TripleSharedPrefs(AddActivity.this.getApplicationContext(), "Main_Activity");
            String[] s = {content, time, location,Integer.toString(id)};
            if (id >= 0 && content.isEmpty() && time.isEmpty() && location.isEmpty()) {
                tsf.remove(Integer.toString(id));
            } else if (id >= 0 && (!content.isEmpty() || !time.isEmpty() || !location.isEmpty())) {
                tsf.edit(Integer.toString(id), s);
            } else if (id < 0 && (!content.isEmpty() || !time.isEmpty() || !location.isEmpty()))
                id = tsf.add(s);
            return null;
        }
    }
}
