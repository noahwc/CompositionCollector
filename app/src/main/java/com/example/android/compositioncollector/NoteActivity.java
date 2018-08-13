package com.example.android.compositioncollector;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseInterface(this);
        note_data = new NoteContent();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote(view);
            }
        });
    }

    public void saveNote(View view){
        note_data.setTitle(((TextInputLayout)findViewById(R.id.title)).getEditText().getText().toString());
        note_data.setLocation(((TextInputLayout)findViewById(R.id.location)).getEditText().getText().toString());
        note_data.setTime(((TextInputLayout)findViewById(R.id.time)).getEditText().getText().toString());
        note_data.setWeather(((TextInputLayout)findViewById(R.id.weather)).getEditText().getText().toString());
        note_data.setGear(((TextInputLayout)findViewById(R.id.gear)).getEditText().getText().toString());
        note_data.setDescription(((TextInputLayout)findViewById(R.id.description)).getEditText().getText().toString());
        if(db.addNote(note_data)) {
            Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show();
        }
        else{
            Snackbar.make(view, "Save Failed", Snackbar.LENGTH_SHORT).show();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
