package com.android.ilab.todoapp;

import com.android.ilab.todoapp.pojos.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyApiEndpointInterface {
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("todos")
    Call<List<Todo>> getTodos();

    @POST("todos")
    Call<Todo> sendTodos(@Body Todo todo);

}