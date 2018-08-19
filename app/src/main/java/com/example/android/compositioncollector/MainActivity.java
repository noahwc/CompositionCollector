package com.example.android.compositioncollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onEntryClickListener{

    private RecyclerViewAdapter r_adapter;
    private RecyclerView r_note_entries;
    DatabaseInterface db;

    boolean first_launch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseInterface(this);

        r_note_entries = findViewById(R.id.recycler_list);
        LinearLayoutManager r_layout_manager = new LinearLayoutManager(this);
        r_adapter = new RecyclerViewAdapter(db.getDBContents(), this);

        r_note_entries.setLayoutManager(r_layout_manager);
        r_note_entries.setHasFixedSize(true);
        r_note_entries.setAdapter(r_adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent new_single_note = new Intent(getBaseContext(), NoteActivity.class);
                startActivity(new_single_note);
            }
        });
    }

    @Override
    public void onEntryClick(int clickedEntryIndex){
        Intent single_note = new Intent(getBaseContext(), NoteActivity.class);
        single_note.putExtra("DB_ROW", clickedEntryIndex);
        startActivity(single_note);
    }

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
