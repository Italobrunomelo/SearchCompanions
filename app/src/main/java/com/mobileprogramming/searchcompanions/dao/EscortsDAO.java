package com.mobileprogramming.searchcompanions.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobileprogramming.searchcompanions.Escorts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by italo on 17/05/2017.
 */

public class EscortsDAO extends SQLiteOpenHelper {

    public EscortsDAO(Context context) {
        super(context, "Search Escorts", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Escorts (name TEXT NOT NULL, information TEXT NOT NULL, " +
                "contact TEXT NOT NULL, location TEXT NOT NULL, status CHAR NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Escorts;";
        db.execSQL(sql);
        onCreate(db);
    }

    //INSERT
    public void insert(Escorts escorts){
        String sql = "INSERT INTO Escorts (name,email,phone) VALUES ('" + escorts.getmName()+"','"+ escorts.getmInformation()+"','"+ escorts.getmContact()+"','"+ escorts.getmLocation()+"','"+ escorts.getmStatus()+"');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    //GetAllCompanions
    public List<Escorts> getAllCompanions(){
        String sql = "SELECT * FROM Escorts;";
        SQLiteDatabase sqLiteDb = getReadableDatabase();
        Cursor cursor = sqLiteDb.rawQuery(sql, null);
        List<Escorts> companion = new ArrayList<Escorts>();

        while(cursor.moveToNext()){
            Escorts escorts = new Escorts();
            escorts.setmName(cursor.getString(cursor.getColumnIndex("name")));
            escorts.setmInformation(cursor.getString(cursor.getColumnIndex("information")));
            escorts.setmContact(cursor.getString(cursor.getColumnIndex("contact")));
            escorts.setmLocation(cursor.getString(cursor.getColumnIndex("location")));
            escorts.setmStatus(cursor.getString(cursor.getColumnIndex("status")));
            companion.add(escorts);
        }

        sqLiteDb.close();

        return companion;
    }
}