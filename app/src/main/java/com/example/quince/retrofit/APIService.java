package com.example.quince.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @POST("/register")
    @Headers({"Content-Type:application/json;charset=UTF-8"})
    Call<Response4Action> registerUser(@Body String user);

}
