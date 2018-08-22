package com.example.android.compositioncollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseInterface  extends SQLiteOpenHelper{

    private static String db_name = "USER_NOTES";
    public String[] db_cols = {"title", "location", "time", "weather", "gear", "description"};

    public DatabaseInterface(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_create = "CREATE TABLE " + db_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT";
        for (String col : db_cols) {
            db_create += ", " + col + " TEXT";
        }
        db_create += ")";
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int current_version, final int new_version) {
        while(current_version < new_version){
            switch(current_version){
                // Each case contains the steps required to upgrade to the current version by one.
            }
            current_version++;
        }
    }

    private ContentValues prepareNote(NoteContent user_data){
        String[] user_data_array = user_data.makeArray();
        ContentValues prepared_note = new ContentValues();
        // TODO: Add error handling for mismatched cols
        for(int i = 0; i < db_cols.length; i++){
            prepared_note.put(db_cols[i], user_data_array[i]);
        }
        return prepared_note;
    }

    public boolean addNote(NoteContent user_data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues to_insert = prepareNote(user_data);
        if(db.insert(db_name, null, to_insert) == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateNote(NoteContent user_data, int row){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues edited = prepareNote(user_data);
        String[] row_id = {getRowId(row)};
        if(db.update(db_name, edited, "id=?", row_id) == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteNote(int row){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] row_id = {getRowId(row)};
        if(db.delete(db_name,  "id=?", row_id) == 1){
            return true;
        }
        else{
            return false;
        }
    }

    private String getRowId(int row){
        Cursor db_sel = cursorAtRow(row);
        return db_sel.getString(0);
    }

    private Cursor cursorGetAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + db_name, null);
    }

    public Cursor cursorAtRow(int row){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor db_sel = this.cursorGetAll();
        db_sel.moveToPosition(row);
        return db_sel;
    }

    public ArrayList<NoteContent> getDBContents(){
        ArrayList<NoteContent> db_dump = new ArrayList<>();
        Cursor db_sel = this.cursorGetAll();

        String[] db_row = new String[db_cols.length];
        while(db_sel.moveToNext()){
            for(int i = 1; i <= db_cols.length; i++){
                db_row[i-1] = db_sel.getString(i);
            }
            NoteContent current_note = new NoteContent();
            current_note.fromArray(db_row);
            db_dump.add(current_note);
        }
        return db_dump;
    }
}
