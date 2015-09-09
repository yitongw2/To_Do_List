package com.example.android.todolist;


/**
 * Created by Tony on 8/31/15.
 */
public class Reminder {
    private String time;
    private String location;
    private String content;
    private int id;
    protected Reminder(String c, String t, String l, String i){
        time=t;
        location=l;
        content=c;
        id=Integer.parseInt(i);
    }
    protected Reminder(){
        this(null,null,null,null);
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
    protected int getId(){return id;}
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
