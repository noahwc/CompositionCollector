package com.example.android.compositioncollector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseInterface  extends SQLiteOpenHelper{

    private static String db_name = "USER_NOTES";
    private String[] db_cols = {"title", "location", "time", "weather", "gear", "description"};

    public DatabaseInterface(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_create = "CREATE TABLE " + db_name + " (ID INT PRIMARY KEY AUTOINCREMENT";
        for (String col : db_cols) {
            db_create += ", " + col + " TEXT";
        }
        db_create += ")";
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int current_version, final int new_version) {
        while(current_version < new_version){
            switch(current_version){
                // Each case contains the steps required to upgrade to the current version by one.
            }
            current_version++;
        }
    }

    public void addNote(NoteContent user_data){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] user_data_array = user_data.makeArray();

    }
}