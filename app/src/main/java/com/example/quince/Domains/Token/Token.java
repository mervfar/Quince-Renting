package com.example.quince.Domains.Token;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Token {

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("token_type")
    private String token_type;

}
