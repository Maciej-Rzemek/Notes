package com.example.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.example.notes.adapters.NotesRecyclerAdapter;
import com.example.notes.models.Note;
import com.example.notes.utils.SpacingItemDecorator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ArrayList<Note> notes = new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        initRecyclerView();
        initFakeNotes();
        setSupportActionBar((Toolbar) findViewById(R.id.notes_toolbar));
        setTitle("Notes");
    }

    private void initFakeNotes() {
        for (int i = 0; i < 100; i++ ) {
            Note note = new Note();
            note.setContent("content " + i);
            note.setTitle("title " + i);
            note.setTimeStamp("Jan 2019");
            notes.add(note);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(notes, this);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(10);
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: Clicked nr: " + position);

        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra("selected notes", notes.get(position));
        startActivity(intent);
    }
}
