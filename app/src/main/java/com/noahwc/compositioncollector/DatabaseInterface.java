package com.noahwc.compositioncollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseInterface  extends SQLiteOpenHelper{

    private static String db_name = "USER_NOTES"; // The SQL table name
    public String[] db_cols = {"title", "location", "time", "weather", "gear", "description"}; // List of the columns of the SQL table.

    /**
     * Generic constructor (see parent class).
     * @param context
     */

    public DatabaseInterface(Context context) {
        super(context, db_name, null, 1);
    }

    /**
     * Build and execute and SQL command to create a table.
     * @param db The database which the table will be held in.
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String db_create = "CREATE TABLE " + db_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT";
        for (String col : db_cols) { // Iterate through the columns and add them to the SQL creation command.
            db_create += ", " + col + " TEXT";
        }
        db_create += ")";
        db.execSQL(db_create);
    }

    /**
     * Handles the updating of the database.
     * @param db Database to update.
     * @param current_version Current version of the database.
     * @param new_version Version the database should be updated to.
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int current_version, final int new_version) {
        while(current_version < new_version){
            switch(current_version){
                // Each case contains the steps required to upgrade to the current version by one.
            }
            current_version++;
        }
    }

    /**
     * Take a NoteContent object and convert it into a ContentValues object.
     * @param user_data The NoteContent object to convert.
     * @return The contructed ContentVales object.
     */

    private ContentValues prepareNote(NoteContent user_data){
        String[] user_data_array = user_data.makeArray();
        ContentValues prepared_note = new ContentValues();
        // TODO: Add error handling for mismatched cols
        for(int i = 0; i < db_cols.length; i++){
            prepared_note.put(db_cols[i], user_data_array[i]);
        }
        return prepared_note;
    }

    /**
     * Parse and add a new note to the database.
     * @param user_data The note to add.
     * @return Boolean indicating success (true) or fail (false).
     */

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

    /**
     * Update an existing note (row) in the database.
     * @param user_data The new note data to use.
     * @param row The row of the existing note's entry.
     * @return Boolean indicating success (true) or fail (false).
     */

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

    /**
     * Delete an existing note (row) in the database.
     * @param row The row to delete.
     * @return Boolean indicating success (true) or fail (false).
     */

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

    /**
     * Get the id of a row based on its position.
     * @param row The row number to get the id of.
     * @return The id of the row.
     */
    private String getRowId(int row){
        Cursor db_sel = cursorAtRow(row);
        return db_sel.getString(0);
    }

    /**
     * Get a cursor encompassing the entire table.
     * @return A cursor selecting the entire table.
     */

    private Cursor cursorGetAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + db_name, null);
    }

    /**
     * Get a cursor selecting a specific row.
     * @param row The row number to select.
     * @return A cursor selecting the requested row.
     */

    public Cursor cursorAtRow(int row){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor db_sel = this.cursorGetAll();
        db_sel.moveToPosition(row);
        return db_sel;
    }

    /**
     * Get the entire contents of the database.
     * @return An ArrayList containing the entire contents of the database. Each row is stored in one element.
     */

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
