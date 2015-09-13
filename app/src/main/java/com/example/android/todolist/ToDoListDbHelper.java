package com.example.android.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tony on 9/11/15.
 */
public class ToDoListDbHelper extends SQLiteOpenHelper {
    protected static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoList.db";
    private static final String TEXT_TYPE=" TEXT";
    private static final String COMMA_SEP=",";
    private static final String SQL_CREATE_TODOLISTDB="CREATE TABLE "+ ToDoListDbContract.ToDoList.TABLE_NAME+
            " ("+ ToDoListDbContract.ToDoList._ID+" INTEGER PRIMARY KEY"+
            COMMA_SEP+ ToDoListDbContract.ToDoList.COLUMN_NAME_CONTENT+TEXT_TYPE+
            COMMA_SEP+ ToDoListDbContract.ToDoList.COLUMN_NAME_TIME+TEXT_TYPE+
            COMMA_SEP+ ToDoListDbContract.ToDoList.COLUMN_NAME_LOCATION+TEXT_TYPE+
            COMMA_SEP+ ToDoListDbContract.ToDoList.COLUMN_NAME_MODIFIEDDATE+TEXT_TYPE+" )";
    private static final String SQL_DELETE_TODOLISTDB="DROP TABLE IF EXISTS "+ ToDoListDbContract.ToDoList.TABLE_NAME;
    public ToDoListDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TODOLISTDB);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_TODOLISTDB);
        onCreate(db);
    }
}
