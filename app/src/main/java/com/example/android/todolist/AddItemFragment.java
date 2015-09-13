package com.example.android.todolist;

import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;


public class AddItemFragment extends Fragment {

    EditText editText_time;
    EditText editText_location;
    EditText editText_content;
    private long id = -1;
    private static final String key="id";
    public AddItemFragment() {
        // Required empty public constructor
    }
    protected void setId(long newId){
        id=newId;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState!=null)
            id=savedInstanceState.getLong(key);
        if (getArguments() != null) {
            id=getArguments().getLong(key);
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        String time = editText_time.getText().toString();
        String location = editText_location.getText().toString();
        String content = editText_content.getText().toString();
        new SaveRecord().execute(content,time,location);
        editText_content=null;
        editText_time=null;
        editText_location=null;
    }
    @Override
    public void onStop(){
        super.onStop();
    }
    @Override
    public void onResume(){
        super.onResume();
        editText_time = (EditText) getActivity().findViewById(R.id.activity_add_time);
        editText_location = (EditText) getActivity().findViewById(R.id.activity_add_location);
        editText_content = (EditText) getActivity().findViewById(R.id.activity_add_content);
        if (id!=-1){
            new FetchRecord().execute(id);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_add_item, container, false);
        Button button=(Button) rootView.findViewById(R.id.activity_add_delete_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoListDb db=new ToDoListDb(getActivity().getApplicationContext());
                db.remove(id);
                getActivity().finish();
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong(key,id);
    }
    private class FetchRecord extends AsyncTask<Long,Void,Reminder>{
        protected Reminder doInBackground(Long... ids){
            ToDoListDb db=new ToDoListDb(getActivity());
            Reminder reminder=db.get(ids[0]);
            return reminder;
        }
        protected void onPostExecute(Reminder result){
            editText_content.setText(result.getContent());
            editText_time.setText(result.getTime());
            editText_location.setText(result.getLocation());
        }
    }
    private class SaveRecord extends AsyncTask<String,Void,Void>{
        protected Void doInBackground(String... strings){
            String content=strings[0];
            String time=strings[1];
            String location=strings[2];
            String[] str={content,time,location,Long.toString(System.currentTimeMillis())};
            ToDoListDb db=new ToDoListDb(getActivity().getApplicationContext());
            if (id != -1 && content.isEmpty() && time.isEmpty() && location.isEmpty())
                db.remove(id);
            else if (id != -1 && (!content.isEmpty() || !time.isEmpty() || !location.isEmpty()))
                db.update(id,str);
            else if (id == -1 && (!content.isEmpty() || !time.isEmpty() || !location.isEmpty()))
                id=db.add(str);
            return null;
        }
    }
}
