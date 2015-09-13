package com.example.android.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ToDoListFragment extends Fragment {
    private CustomAdapter customAdapter;
    private ArrayList<Reminder> reminderArrayList;
    private View rootView;
    private OnItemSelectedListener mCallback;
    public interface OnItemSelectedListener{
        public void onAddItem();
    }
    public ToDoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public void onPause(){
        super.onPause();
    }
    public void onResume(){
        super.onResume();
        new LoadList().execute(getActivity());
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_to_do_list_fragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_add:
                mCallback.onAddItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_to_do_list, container, false);
        ListView listView=(ListView) rootView.findViewById(R.id.list_view1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long on_Clicked_id=reminderArrayList.get(position).getId();
                Intent intent=new Intent(getActivity(),AddActivity.class);
                intent.putExtra(MainActivity.key,on_Clicked_id);
                startActivity(intent);
            }
        });
        return rootView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    private class LoadList extends AsyncTask<Context,Void,CustomAdapter>{
        protected CustomAdapter doInBackground(Context... contexts){
            ToDoListDb listDb=new ToDoListDb(contexts[0].getApplicationContext());
            reminderArrayList=listDb.getAll();
            customAdapter=new CustomAdapter(contexts[0].getApplicationContext(),reminderArrayList);
            return customAdapter;
        }
        protected void onPostExecute(CustomAdapter result){
            ListView listView=(ListView) rootView.findViewById(R.id.list_view1);
            listView.setAdapter(customAdapter);
        }
    }
}
