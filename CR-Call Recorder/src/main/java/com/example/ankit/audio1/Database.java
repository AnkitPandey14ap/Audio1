package com.example.ankit.audio1;

/**
 * Created by ankit on 4/2/17.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.util.Log.i;
public class Database {
    private static final String DATABASE_NAME ="call.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="Recordings";

    private static final String COLOUMN_ID="id";
    private static final String COLOUMN_NAME="name";
    private static final String COLOUMN_TIME="time";
    private static final String COLOUMN_DATE="date";
    private static final String COLOUMN_NUMBER="number";

    private static final String CREATE_TABLE_QUERY=
            "create table "+TABLE_NAME+" ("+COLOUMN_ID+" integer primary key autoincrement,"+COLOUMN_NAME+" text,"+COLOUMN_NUMBER+" integer,"+COLOUMN_DATE+" text,"+COLOUMN_TIME+" text);";

    private RecordingsDBHelper helper;
    //SQLiteDatabase is responsibel for iserting, updating deletinf from the database
    private static SQLiteDatabase sqLiteDatabase;

    Database(Context context){
        helper=new RecordingsDBHelper(context);
    }

    void open(){
        //getwrightableDatabase gives us the object of the SQLiteDatabase class
        sqLiteDatabase=helper.getWritableDatabase();
    }

    void close(){
        if(sqLiteDatabase!=null){
            sqLiteDatabase.close();
        }
    }
    void insertRecord(String name){
        ContentValues values = new ContentValues();
        values.put(COLOUMN_NAME,name);

        long rowId=sqLiteDatabase.insert(TABLE_NAME,null,values);

        if(rowId==-1)
            i("Ankit","Error in insering the values");
        else
            i("Ankit", "inserted succefully "+rowId);
    }

    int readRecord(){
        String id = "0";
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor!=null && cursor.moveToLast()){

                //int nameColumnIndex=cursor.getColumnIndex(COLOUMN_NAME);
            int idColumnIndex=cursor.getColumnIndex(COLOUMN_ID);
                //String name=cursor.getString(nameColumnIndex);
            id=cursor.getString(idColumnIndex);
                //Log.i("Ankit",name+" "+id);
            cursor.close();

        }
        return Integer.valueOf(id);
    }
    void deleteRecords(){
        int count=sqLiteDatabase.delete(TABLE_NAME,COLOUMN_NAME+" =?",new String[]{"Ankit"});
        //int count=sqLiteDatabase.delete(TABLE_NAME,null,null);
        Log.i("Ankit", "Count "+count);
    }


    private class RecordingsDBHelper extends SQLiteOpenHelper {
        public RecordingsDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.i("Ankit","reached in final");
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_QUERY);
            Log.i("Ankit","table created");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            i("Ankit","reached in new");
        }
    }
}



