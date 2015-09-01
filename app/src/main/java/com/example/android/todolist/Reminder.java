package com.example.android.todolist;


/**
 * Created by Tony on 8/31/15.
 */
public class Reminder {
    private String time;
    private String location;
    private String content;
    protected Reminder(String c, String t, String l){
        time=t;
        location=l;
        content=c;
    }
    protected Reminder(){
        this(null,null,null);
    }
    protected String getTime(){
        return time;
    }
    protected String getLocation(){
        return location;
    }
    protected String getContent(){
        return content;
    }
    protected void setTime(String t){
        time=t;
    }
    protected void setLocation(String l){
        location=l;
    }
    protected void setContent(String c){
        content=c;
    }
}
