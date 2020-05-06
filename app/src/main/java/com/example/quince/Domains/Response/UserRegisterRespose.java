package com.example.quince.Domains.Response;

import com.google.gson.annotations.SerializedName;

import javax.net.ssl.SSLContext;

import lombok.Data;

@Data
public class UserRegisterRespose {

    @SerializedName("result")
    private String result;

    @SerializedName("info")
    private String info;
}
