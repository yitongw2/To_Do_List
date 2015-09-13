package com.example.android.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Tony on 9/11/15.
 */
public class ToDoListDb {
    SQLiteDatabase db;
    public ToDoListDb(Context context){
        ToDoListDbHelper toDoListDbHelper=new ToDoListDbHelper(context);
        db=toDoListDbHelper.getWritableDatabase();
    }
    protected long add(String[] strs){
        ContentValues contentValues=new ContentValues();
        contentValues.put(ToDoListDbContract.ToDoList.COLUMN_NAME_CONTENT,strs[0]);
        contentValues.put(ToDoListDbContract.ToDoList.COLUMN_NAME_TIME,strs[1]);
        contentValues.put(ToDoListDbContract.ToDoList.COLUMN_NAME_LOCATION,strs[2]);
        contentValues.put(ToDoListDbContract.ToDoList.COLUMN_NAME_MODIFIEDDATE,Long.valueOf(strs[3]));
        long newRowId=db.insert(ToDoListDbContract.ToDoList.TABLE_NAME, null,contentValues);
        return newRowId;
    }
    protected void update(long id, String[] strs){
        ContentValues contentValues=new ContentValues();
        contentValues.put(ToDoListDbContract.ToDoList.COLUMN_NAME_CONTENT,strs[0]);
        contentValues.put(ToDoListDbContract.ToDoList.COLUMN_NAME_TIME,strs[1]);
        contentValues.put(ToDoListDbContract.ToDoList.COLUMN_NAME_LOCATION, strs[2]);
        contentValues.put(ToDoListDbContract.ToDoList.COLUMN_NAME_MODIFIEDDATE, Long.valueOf(strs[3]));
        String selection = ToDoListDbContract.ToDoList._ID+" LIKE ?";
        String[] selectionArgs={String.valueOf(id)};
        db.update(ToDoListDbContract.ToDoList.TABLE_NAME,contentValues,selection,selectionArgs);
    }
    protected ArrayList<Reminder> getAll(){
        Cursor result=read();
        result.moveToFirst();
        ArrayList<Reminder> reminders=new ArrayList<Reminder>();
        while (result.moveToNext()){
            reminders.add(cursorToReminder(result));
        }
        return reminders;
    }
    protected Reminder get(long id){
        Cursor result=fetch(id);
        result.moveToFirst();
        return cursorToReminder(result);
    }
    protected long remove(long id){
        String selection= ToDoListDbContract.ToDoList._ID+" LIKE?";
        String[] selectionArgs={String.valueOf(id)};
        db.delete(ToDoListDbContract.ToDoList.TABLE_NAME,selection,selectionArgs);
        return id;
    }
    private Cursor fetch(long id){
        String[] projection={ToDoListDbContract.ToDoList._ID, ToDoListDbContract.ToDoList.COLUMN_NAME_CONTENT,
                ToDoListDbContract.ToDoList.COLUMN_NAME_TIME, ToDoListDbContract.ToDoList.COLUMN_NAME_LOCATION,
                ToDoListDbContract.ToDoList.COLUMN_NAME_MODIFIEDDATE};
        String selection= ToDoListDbContract.ToDoList._ID+" LIKE ?";
        String[] selectionArgs= {String.valueOf(id)};
        String sortOrder= ToDoListDbContract.ToDoList.COLUMN_NAME_MODIFIEDDATE+" DESC";
        Cursor result=db.query(ToDoListDbContract.ToDoList.TABLE_NAME,
                projection, selection, selectionArgs, null, null, sortOrder);
        return result;
    }
    protected Cursor read(){
        Cursor result=db.rawQuery("SELECT * FROM " + ToDoListDbContract.ToDoList.TABLE_NAME, null);
        return result;
    }
    private Reminder cursorToReminder(Cursor result) {
        if (result != null) {
            long result_id = result.getLong(result.getColumnIndex(ToDoListDbContract.ToDoList._ID));
            String content = result.getString(result.getColumnIndexOrThrow(ToDoListDbContract.ToDoList.COLUMN_NAME_CONTENT));
            String time = result.getString(result.getColumnIndexOrThrow(ToDoListDbContract.ToDoList.COLUMN_NAME_TIME));
            String location = result.getString(result.getColumnIndexOrThrow(ToDoListDbContract.ToDoList.COLUMN_NAME_LOCATION));
            Long modifiedDate = result.getLong(result.getColumnIndexOrThrow(ToDoListDbContract.ToDoList.COLUMN_NAME_MODIFIEDDATE));
            return new Reminder(content, time, location, result_id, modifiedDate);
        }
        return null;
    }
}
