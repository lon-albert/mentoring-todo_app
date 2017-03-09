package com.android.ilab.todoapp.pojos;

import com.google.gson.annotations.SerializedName;

public class Todo {

  @SerializedName("_id")
  String id;

  @SerializedName("__v")
  int v;

  @SerializedName("text")
  private String title;

  private String detail;

  public Todo(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }
}