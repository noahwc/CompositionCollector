package com.example.android.compositioncollector;

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

    public interface onEntryClickListener {
        void onEntryClick(int clickedEntryIndex);
    }

    public RecyclerViewAdapter(ArrayList<NoteContent> db_contents, onEntryClickListener activity_click_listener){
        entries = db_contents;
        num_of_entries = db_contents.size();
        click_listener = activity_click_listener;
    }


    public class RViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView list_entry;

        public RViewHolder(View title_view) {
            super(title_view);
            list_entry = title_view.findViewById(R.id.list_entry_slot);
            title_view.setOnClickListener(this);
        }

        @Override
        public void onClick(View entry_view){
            click_listener.onEntryClick(getAdapterPosition());
        }
    }

    public int getItemCount(){
        return num_of_entries;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layout_inflate = LayoutInflater.from(parent.getContext());
        View entry_view = layout_inflate.inflate(R.layout.note_list_entry, parent, false);
        RViewHolder entry_holder = new RViewHolder(entry_view);
        return entry_holder;
    }

    @Override
    public void onBindViewHolder(RViewHolder entry_holder, int list_position){
        String curent_entry_title = entries.get(list_position).getTitle();
        entry_holder.list_entry.setText(curent_entry_title);
    }
}
