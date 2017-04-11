package com.android.ilab.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.ilab.todoapp.adapters.MyAdapter;
import com.android.ilab.todoapp.database.UserDB;
import com.android.ilab.todoapp.pojos.Todo;
import com.android.ilab.todoapp.pojos.User;
import com.android.ilab.todoapp.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://fierce-ocean-30542.herokuapp.com/api/";
    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;


    SessionManager sessionManager;
    UserDB userdb;
    User user;

    //    String [] myDataset = {"Wash Car", "Eat food", "Buy clothes", "Go to church", "Watch soccer"};
    String [] myContent = {
            "Aliquam lorem ante, das in, viverra quis, feugiat a, tellus. Etiam sit amet orci eget eros faucibus tincidunt. Curabitur ullamcorper ultricies nisi.",
            "Maecenas egestas arcu quis ligula mattis placerat. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean massa. Vivamus consectetuer hendrerit lacus.",
            "Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Phasellus viverra nulla ut metus varius laoreet.",
            "Aenean ut eros et nisl sagittis vestibulum. Cras sagittis. Aliquam erat volutpat.",
            "Praesent metus tellus elementum eu  sagittis vestibulum. Cras sagittis. Aliq"};

    List<Todo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(this);
        userdb = new UserDB(this);

        recyclerView =  (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        todos = new ArrayList<Todo>();

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(todos, getBaseContext());
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateTodoActivity.class));
            }
        });

        user = userdb.getUserDetails();
        Toast.makeText(getBaseContext(), "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();

        getTodos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getTitle().toString().equals(getResources().getString(R.string.logout))){
            sessionManager.setLogin(false);
            Toast.makeText(getBaseContext(), "We're sorry to see you go, " + user.getUsername(), Toast.LENGTH_SHORT).show();
            userdb.deleteUsers();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getTodos() {

        Retrofit.Builder builder = new Retrofit.Builder();

        Retrofit retrofit = builder
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);

        Call<List<Todo>> call = apiService.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                todos.clear();
                todos.addAll(response.body());
//                todos = populateTodos();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                Toast.makeText(getBaseContext(), "An error occured: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
