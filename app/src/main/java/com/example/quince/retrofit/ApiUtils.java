package com.example.quince.retrofit;

public class ApiUtils {

    private ApiUtils(){
    }
    public static final String BASE_URL="https://mervfar.com:8080/";
    public static APIService getAPIService(){
        return  RetrofitClient.getClient(BASE_URL).create(APIService.class);

    }
}
