package com.example.android.todolist;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Tony on 9/2/15.
 */
public class TripleSharedPrefs {
    SharedPreferences sharedPref_content;
    SharedPreferences sharedPref_time;
    SharedPreferences sharedPref_location;
    SharedPreferences sharedPref_key;
    int size;
    public TripleSharedPrefs(Context context, String activity_name){
        sharedPref_content=context.getSharedPreferences(activity_name+"_content", Context.MODE_PRIVATE);
        sharedPref_time=context.getSharedPreferences(activity_name+"_time", Context.MODE_PRIVATE);
        sharedPref_location=context.getSharedPreferences(activity_name+"_location", Context.MODE_PRIVATE);
        sharedPref_key=context.getSharedPreferences(activity_name+"_key", Context.MODE_PRIVATE);
        this.context=context;
    }
    protected ArrayList<Reminder> getAll(){
        ArrayList<Reminder> result=new ArrayList<Reminder>();
        for (String key:sharedPref_key.getAll().keySet()){
            result.add(new Reminder(sharedPref_content.getString(key,null),sharedPref_time.getString(key,null),sharedPref_location.getString(key,null)));
        }
        return result;
    }
    protected void add(String[] str){
        String new_key=addKey();
        addContent(new_key,str[0]);
        addTime(new_key,str[1]);
        addLocation(new_key,str[2]);
    }
    private String addKey(){
        SharedPreferences.Editor key_editor=sharedPref_key.edit();
        String new_key=Integer.toString(sharedPref_key.getAll().size()+1);
        key_editor.putString(new_key,new_key);
        key_editor.commit();
        return new_key;
    }
    private void addContent(String key, String c){
        SharedPreferences.Editor content_editor=sharedPref_content.edit();
        content_editor.putString(key,c);
        content_editor.commit();
    }
    private void addTime(String key, String t){
        SharedPreferences.Editor time_editor=sharedPref_time.edit();
        time_editor.putString(key,t);
        time_editor.commit();
    }
    private  void addLocation(String key, String l){
        SharedPreferences.Editor location_editor=sharedPref_location.edit();
        location_editor.putString(key,l);
        location_editor.commit();
    }
}
