package com.example.quince.Configuration.RestService;

import com.example.quince.Domains.Car.CarViewDTO;
import com.example.quince.Domains.Response.UserRegisterRespose;
import com.example.quince.Domains.Token.Token;
import com.example.quince.Domains.User.UserAddDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    // Burda token almamızı sağlayan endpoint var
    @GET("oauth/token")
    Call<Token> getToken(
            @Query("username") String username,
            @Query("password") String password,
            @Query("grant_type") String grant_type, // yetki türü
            @Header("Authorization") String secret
    );

    // Arabaları listeleyen servis
    @GET("api/car")
    Call<List<CarViewDTO>> getAllCars();

    //Register For New Users
    @POST("register")
    Call<UserRegisterRespose> register(@Body UserAddDTO newUser);
}
