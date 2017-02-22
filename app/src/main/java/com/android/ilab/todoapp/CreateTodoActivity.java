package com.android.ilab.todoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class CreateTodoActivity extends AppCompatActivity {

    AutoCompleteTextView titleView;
    EditText detailView;
    AppCompatButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleView = (AutoCompleteTextView) findViewById(R.id.title);
        detailView = (EditText) findViewById(R.id.detail);
        sendButton = (AppCompatButton) findViewById(R.id.create_button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sendButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postData();
                    }
                }
        );

    }

    private void postData() {
        if(!validateData()) {
            return;
        }

        // TODO Implement posting to server
    }

    private boolean validateData() {
        String title = titleView.getText().toString();
        String detail = detailView.getText().toString();

        if(title.length() < 3 || title.length() > 20){
            titleView.setError("Must be between 3 and 20 characters");
        }

        if((detail.length() < 5 || detail.length() > 200)){
            detailView.setError("Must be between 5 and 200 characters");
        }

        return false;

    }


}
