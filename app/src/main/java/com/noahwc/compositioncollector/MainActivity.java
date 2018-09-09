package com.noahwc.compositioncollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * This class displays a list, using recycler view, of all the notes stored in the app in chronological order based on their creation date.
 */

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onEntryClickListener{

    private RecyclerViewAdapter r_adapter;
    private RecyclerView r_note_entries;
    DatabaseInterface db;

    // Keep track of if this is the first run of this activity in the app's instance.
    boolean first_launch = true;

    /**
     * Mainly handles the initialization of the recycler view and the floating action button.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseInterface(this); // Gain access to the database of notes.

        // Initialize the recycler view.
        r_note_entries = findViewById(R.id.recycler_list);
        LinearLayoutManager r_layout_manager = new LinearLayoutManager(this);
        r_adapter = new RecyclerViewAdapter(db.getDBContents(), this);
        r_note_entries.setLayoutManager(r_layout_manager);
        r_note_entries.setHasFixedSize(true);
        r_note_entries.setAdapter(r_adapter);
        r_layout_manager.setReverseLayout(true);
        r_layout_manager.setStackFromEnd(true);

        //Initialize the new note floating action button.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_single_note = new Intent(getBaseContext(), NoteActivity.class);
                startActivity(new_single_note);
            }
        });
    }

    /**
     * Open a single note upon click.
     * @param clickedEntryIndex The item (note) in the list which was clicked.
     */

    @Override
    public void onEntryClick(int clickedEntryIndex){
        Intent single_note = new Intent(getBaseContext(), NoteActivity.class);
        single_note.putExtra("DB_ROW", clickedEntryIndex);
        startActivity(single_note);
    }

    /**
     * Update the list data on return to the list after a note is edited. Don't update on initial list creation.
     */

    @Override
    protected void onResume() {
        super.onResume();
        if(!first_launch) {
            r_adapter.updateEntries(db.getDBContents());
            r_adapter.notifyDataSetChanged();
        }
        else {
            first_launch = false;
        }
    }

}
