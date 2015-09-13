package com.example.android.todolist;

import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
    private static final String key = "id";

    public AddItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("to_do_list", "onCreateFragment");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        Log.d("to_do_list", "onPauseFragment");
        super.onPause();
        editText_time = (EditText) getActivity().findViewById(R.id.activity_add_time);
        editText_location = (EditText) getActivity().findViewById(R.id.activity_add_location);
        editText_content = (EditText) getActivity().findViewById(R.id.activity_add_content);
        String time = editText_time.getText().toString();
        String location = editText_location.getText().toString();
        String content = editText_content.getText().toString();
        doInBackground(content,time,location);
        editText_content = null;
        editText_time = null;
        editText_location = null;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.d("to_do_list", "onResumeFragment");
        super.onResume();
        editText_time = (EditText) getActivity().findViewById(R.id.activity_add_time);
        editText_location = (EditText) getActivity().findViewById(R.id.activity_add_location);
        editText_content = (EditText) getActivity().findViewById(R.id.activity_add_content);
        if (id != -1) {
            new FetchRecord().execute(id);
            Log.d("to_do_list", "onResumeRestoreIdFragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("to_do_list", "onCreateViewFragment");
        if (savedInstanceState != null) {
            id = savedInstanceState.getLong(key);
            Log.d("to_do_list", "onRestoreIdFragment");
        }
        if (getArguments() != null) {
            id = getArguments().getLong(key);
            Log.d("to_do_list", "onRestoreArgsFragment");
        }
        View rootView = inflater.inflate(R.layout.fragment_add_item, container, false);
        Button button = (Button) rootView.findViewById(R.id.activity_add_delete_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoListDb db = new ToDoListDb(getActivity().getApplicationContext());
                db.remove(id);
                getActivity().finish();
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Activity activity) {
        Log.d("to_do_list", "onAttachFragment");
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        Log.d("to_do_list", "onDetachFragment");
        super.onDetach();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d("to_do_list", "onSaveInstanceStateFragment" + Long.toString(id));
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong(key, id);
    }

    private class FetchRecord extends AsyncTask<Long, Void, Reminder> {
        protected Reminder doInBackground(Long... ids) {
            ToDoListDb db = new ToDoListDb(getActivity());
            Reminder reminder = db.get(ids[0]);
            return reminder;
        }

        protected void onPostExecute(Reminder result) {
            editText_content.setText(result.getContent());
            editText_time.setText(result.getTime());
            editText_location.setText(result.getLocation());
        }
    }

    protected void doInBackground(String... strings) {
        Log.d("to_do_list", "SaveRecord");
        String content = strings[0];
        String time = strings[1];
        String location = strings[2];
        String[] str = {content, time, location, Long.toString(System.currentTimeMillis())};
        ToDoListDb db = new ToDoListDb(getActivity().getApplicationContext());
        if (id != -1 && content.isEmpty() && time.isEmpty() && location.isEmpty())
            db.remove(id);
        else if (id != -1 && (!content.isEmpty() || !time.isEmpty() || !location.isEmpty()))
            db.update(id, str);
        else if (id == -1 && (!content.isEmpty() || !time.isEmpty() || !location.isEmpty()))
            id = db.add(str);
    }

}

