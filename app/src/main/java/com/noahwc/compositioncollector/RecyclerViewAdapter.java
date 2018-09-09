package com.noahwc.compositioncollector;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RViewHolder>{

    private ArrayList<NoteContent> entries;
    private int num_of_entries;
    final private onEntryClickListener click_listener;

    /**
     * Listen for a click on a note from the list.
     */

    public interface onEntryClickListener {
        void onEntryClick(int clickedEntryIndex);
    }

    /**
     * The constructor initializes member variables.
     * @param db_contents  The contents of the database used to populate the list.
     * @param activity_click_listener
     */
    public RecyclerViewAdapter(ArrayList<NoteContent> db_contents, onEntryClickListener activity_click_listener){
        this.entries = db_contents;
        this.num_of_entries = db_contents.size();
        this.click_listener = activity_click_listener;
    }


    // A standard view holder implementation with a click listener

    public class RViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView list_entry;

        /**
         * A standard view holder constructor.
         * @param title_view A simple text view to be used in the recycler view.
         */

        public RViewHolder(View title_view) {
            super(title_view);
            this.list_entry = title_view.findViewById(R.id.list_entry_slot);
            title_view.setOnClickListener(this);
        }

        /**
         * Handles the click listener.
         * @param entry_view
         */

        @Override
        public void onClick(View entry_view){
            click_listener.onEntryClick(getAdapterPosition());
        }
    }

    /**
     * Get the number of items in the recycler view.
     * @return The number of entries in the recycler view.
     */

    public int getItemCount(){
        return num_of_entries;
    }

    /**
     * Standard onCreateViewHolder that inflates the layouts of the list items.
     * @param parent
     * @param viewType
     * @return
     */

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layout_inflate = LayoutInflater.from(parent.getContext());
        View entry_view = layout_inflate.inflate(R.layout.note_list_entry, parent, false);
        RViewHolder entry_holder = new RViewHolder(entry_view);
        return entry_holder;
    }

    /**
     * Populate the layout with data.
     * @param entry_holder The layout to populate.
     * @param list_position The entry of the database to use to populate view.
     */
    @Override
    public void onBindViewHolder(RViewHolder entry_holder, int list_position){
        String curent_entry_title = entries.get(list_position).getTitle();
        entry_holder.list_entry.setText(curent_entry_title);
    }

    /**
     * Refresh the view upon database update.
     * @param updated_db_contents The new database contents.
     */

    public void updateEntries(ArrayList<NoteContent> updated_db_contents){
        this.entries = updated_db_contents;
        this.num_of_entries = entries.size();
    }
}
