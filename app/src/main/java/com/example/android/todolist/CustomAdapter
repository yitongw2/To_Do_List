package com.example.android.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Tony on 8/31/15.
 */
public class CustomAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<Reminder> reminders;
    private class ViewHolder{
        protected TextView textView1;
        protected TextView textView2;
        protected ViewHolder(){
        }
    }
    public CustomAdapter(Context context, ArrayList<Reminder> list){
        inflater= LayoutInflater.from(context);
        reminders=list;
    }
    public int getCount(){
        return reminders.size();
    }
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder=new ViewHolder();
        if (convertView==null){
            convertView=inflater.inflate(R.layout.list_text_item,null);
            holder.textView1=(TextView) convertView.findViewById(R.id.list_item_text1);
            holder.textView2=(TextView) convertView.findViewById(R.id.list_item_text2);
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        holder.textView1.setText(reminders.get(position).getContent());
        holder.textView2.setText(reminders.get(position).getTime());
        return convertView;
    }
    public long getItemId(int index){
        return index;
    }
    public Reminder getItem(int index){
        return reminders.get(index);
    }


}
