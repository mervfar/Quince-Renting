package com.example.quince.Domains.User;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class UserViewDTO {

    @SerializedName("id")
    private Long id;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("TCno")
    private long TCno;

    @SerializedName("birthDate")
    private int birthDate;

    @SerializedName("userRole")
    private Role userRole;
}
