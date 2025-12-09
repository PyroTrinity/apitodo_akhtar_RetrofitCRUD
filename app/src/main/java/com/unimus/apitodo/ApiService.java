package com.unimus.apitodo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("get_todo.php")
    Call<TodoResponse> getTodo();

    @FormUrlEncoded
    @POST("add_todo.php")
    Call<BasicResponse> addTodo(
            @Field("title") String title
    );

    @FormUrlEncoded
    @POST("delete_todo.php")
    Call<BasicResponse> deleteTodo(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("update_todo.php")
    Call<BasicResponse> updateTodo(
            @Field("id") int id,
            @Field("title") String title
    );

}
