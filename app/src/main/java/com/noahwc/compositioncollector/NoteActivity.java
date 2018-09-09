package com.noahwc.compositioncollector;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This class handles the displaying, editing, and saving of one note.
 */

public class NoteActivity extends AppCompatActivity {
    private NoteContent note_data;
    private DatabaseInterface db;
    private int db_row;

    /**
     * Handles the initialization of the floating action button, the action bar, and various member variables.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // Initializes the action bar to display a back button and for the delete button to be added.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseInterface(this);
        note_data = new NoteContent();

        // Get the location of the opened note in the database. A value of -1 indicates this is a new note not present in the database.
        Intent creator_intent = getIntent();
        db_row = creator_intent.getIntExtra("DB_ROW", -1);

        // If the note does already exist in the database, display it.
        if(db_row != -1){
            this.populateFields();
        }

        //Initialize the save floating action button.
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote(view);
            }
        });
    }

    /**
     * Fill the form fields with the appropriate preexisting data from the database.
     */

    public void populateFields(){
        Cursor db_contents = db.cursorAtRow(db_row);
        ((TextInputLayout)findViewById(R.id.title)).getEditText().setText(db_contents.getString(1));
        ((TextInputLayout)findViewById(R.id.location)).getEditText().setText(db_contents.getString(2));
        ((TextInputLayout)findViewById(R.id.time)).getEditText().setText(db_contents.getString(3));
        ((TextInputLayout)findViewById(R.id.weather)).getEditText().setText(db_contents.getString(4));
        ((TextInputLayout)findViewById(R.id.gear)).getEditText().setText(db_contents.getString(5));
        ((TextInputLayout)findViewById(R.id.description)).getEditText().setText(db_contents.getString(6));
    }

    /**
     * Save the note as a newly created note.
     * @param view View to hold the save confirmation.
     */

    private void saveNewNote(View view){
        if(db.addNote(note_data)) {
            Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show();
        }
        else{
            Snackbar.make(view, "Save Failed", Snackbar.LENGTH_SHORT).show();
        }
    }

    /**
     * Save the note by updating an existing entry.
     * @param view
     */

    private void editNote(View view){
        if(db.updateNote(note_data, db_row)) {
            Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show();
        }
        else{
            Snackbar.make(view, "Save Failed", Snackbar.LENGTH_SHORT).show();
        }
    }

    /**
     * Get the note data from the text fields and store it in a data structure.
     */

    private void getNoteData(){
        note_data.setTitle(((TextInputLayout)findViewById(R.id.title)).getEditText().getText().toString());
        note_data.setLocation(((TextInputLayout)findViewById(R.id.location)).getEditText().getText().toString());
        note_data.setTime(((TextInputLayout)findViewById(R.id.time)).getEditText().getText().toString());
        note_data.setWeather(((TextInputLayout)findViewById(R.id.weather)).getEditText().getText().toString());
        note_data.setGear(((TextInputLayout)findViewById(R.id.gear)).getEditText().getText().toString());
        note_data.setDescription(((TextInputLayout)findViewById(R.id.description)).getEditText().getText().toString());
    }

    /**
     * Save a note.
     * @param view Used by called methods for snackbar creation.
     */

    private void saveNote(View view){
        this.getNoteData();
        if(db_row < 0){
            this.saveNewNote(view);
        }
        else{
            this.editNote(view);
        }
    }

    /**
     * Adds items (delete only at this time) to the top toolbar.
     * @param menu The generated menu.
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_note, menu);
        return true;
    }

    /**
     * Handles clicks of menu items (only delete at this time).
     * @param item The clicked menu item.
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_delete) {
            if(db_row == -1){
                finish();
            }
            else if(db.deleteNote(db_row))
                finish();
            else {
                Snackbar.make(findViewById(android.R.id.content), "Deletion Failed", Snackbar.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
