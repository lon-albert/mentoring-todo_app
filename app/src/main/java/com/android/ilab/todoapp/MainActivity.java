package com.android.ilab.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.ilab.todoapp.adapters.MyAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;
    String [] myDataset = {"Wash Car", "Eat food", "Buy clothes", "Go to church", "Watch soccer"};
    String [] myContent = {
            "Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Etiam sit amet orci eget eros faucibus tincidunt. Curabitur ullamcorper ultricies nisi.",
            "Maecenas egestas arcu quis ligula mattis placerat. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean massa. Vivamus consectetuer hendrerit lacus.",
            "Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Phasellus viverra nulla ut metus varius laoreet.",
            "Aenean ut eros et nisl sagittis vestibulum. Cras sagittis. Aliquam erat volutpat.",
            "Praesent metus tellus elementum eu  sagittis vestibulum. Cras sagittis. Aliq"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView =  (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset, myContent, getBaseContext());
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateTodoActivity.class));
            }
        });

        Toast.makeText(getBaseContext(), "Welcome", Toast.LENGTH_SHORT).show();
    }


}
