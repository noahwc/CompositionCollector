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

public class NoteActivity extends AppCompatActivity {
    private NoteContent note_data;
    private DatabaseInterface db;
    private int db_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent creator_intent = getIntent();
        db_row = creator_intent.getIntExtra("DB_ROW", -1);

        db = new DatabaseInterface(this);
        note_data = new NoteContent();

        if(db_row != -1){
            this.populateFields();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote(view);
            }
        });
    }

    public void populateFields(){
        Cursor db_contents = db.cursorAtRow(db_row);
        ((TextInputLayout)findViewById(R.id.title)).getEditText().setText(db_contents.getString(1));
        ((TextInputLayout)findViewById(R.id.location)).getEditText().setText(db_contents.getString(2));
        ((TextInputLayout)findViewById(R.id.time)).getEditText().setText(db_contents.getString(3));
        ((TextInputLayout)findViewById(R.id.weather)).getEditText().setText(db_contents.getString(4));
        ((TextInputLayout)findViewById(R.id.gear)).getEditText().setText(db_contents.getString(5));
        ((TextInputLayout)findViewById(R.id.description)).getEditText().setText(db_contents.getString(6));
    }

    private void saveNewNote(View view){
        if(db.addNote(note_data)) {
            Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show();
        }
        else{
            Snackbar.make(view, "Save Failed", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void editNote(View view){
        if(db.updateNote(note_data, db_row)) {
            Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show();
        }
        else{
            Snackbar.make(view, "Save Failed", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void getNoteData(){
        note_data.setTitle(((TextInputLayout)findViewById(R.id.title)).getEditText().getText().toString());
        note_data.setLocation(((TextInputLayout)findViewById(R.id.location)).getEditText().getText().toString());
        note_data.setTime(((TextInputLayout)findViewById(R.id.time)).getEditText().getText().toString());
        note_data.setWeather(((TextInputLayout)findViewById(R.id.weather)).getEditText().getText().toString());
        note_data.setGear(((TextInputLayout)findViewById(R.id.gear)).getEditText().getText().toString());
        note_data.setDescription(((TextInputLayout)findViewById(R.id.description)).getEditText().getText().toString());
    }

    private void saveNote(View view){
        this.getNoteData();
        if(db_row < 0){
            this.saveNewNote(view);
        }
        else{
            this.editNote(view);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_delete) {
            if(db.deleteNote(db_row))
                finish();
            else {
                Snackbar.make(findViewById(android.R.id.content), "Deletion Failed", Snackbar.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
