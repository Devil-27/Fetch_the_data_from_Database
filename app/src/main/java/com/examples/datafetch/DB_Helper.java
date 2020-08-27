package com.examples.datafetch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DB_Helper extends SQLiteOpenHelper {
    public static final String DB_name = "Student.db";
    public static final String TB_name = "Student_Table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "NAME";
    public static final String Col_3 = "PHONE";
    public DB_Helper(Context context) {
        super(context,DB_name,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TB_name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , PHONE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_name);
        onCreate(db);
    }
    public boolean insertData(String name , String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME" , name);
        cv.put("PHONE" , phone);
        long result;
        result = db.insert(TB_name, null, cv);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getData() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cu = db.rawQuery("select * from " + TB_name , null );
        return cu;
    }

}
