package com.android.ilab.todoapp;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.ilab.todoapp.pojos.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.ilab.todoapp.MainActivity.BASE_URL;

public class CreateTodoActivity extends AppCompatActivity {

    AutoCompleteTextView titleView;
    EditText detailView;
    boolean done = true ;
    AppCompatButton sendButton;
    public static final String BASE_URL = "https://fierce-ocean-30542.herokuapp.com/api/";
    Todo toder ;

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
        toder = new Todo();



        sendButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toder.setTitle(titleView.getText().toString());
                        toder.setDetail(detailView.getText().toString());
                        toder.setDone(true);
                        postData();
                    }
                }
        );



    }
//    public void CreateToDoActivity(String title, String details, boolean don){
//
//    }

    private void postData() {
        if(validateData()) {
            return;
        }
        else {

            Retrofit.Builder builder = new Retrofit.Builder();

            Retrofit retrofit = builder
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);
            Call<Todo> call = apiService.sendTodos(toder);
            call.enqueue(new Callback<Todo>() {
                @Override
                public void onResponse(Call<Todo> call, Response<Todo> response) {
                    Toast.makeText(CreateTodoActivity.this, "ToDo created successfully", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Todo> call, Throwable t) {
                    Toast.makeText(CreateTodoActivity.this, "ToDo not created", Toast.LENGTH_SHORT).show();

                }
            });


            // TODO Implement posting to server
        }
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
