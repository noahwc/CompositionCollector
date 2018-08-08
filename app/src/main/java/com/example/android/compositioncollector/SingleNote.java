package com.example.android.compositioncollector;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class SingleNote extends AppCompatActivity {

    NoteContent note_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note_data.setTitle(((EditText)findViewById(R.id.title)).getText().toString());
                note_data.setDescription(((EditText)findViewById(R.id.description)).getText().toString());
                note_data.setLocation(((EditText)findViewById(R.id.location)).getText().toString());
                note_data.setWeather(((EditText)findViewById(R.id.weather)).getText().toString());
                note_data.setTime(((EditText)findViewById(R.id.time)).getText().toString());

                Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT).show();
            }
        });
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
