package com.example.quince.Domains.Token;

import android.util.Base64;

import lombok.Data;

@Data
public class Secret {
    private String username="quince";
    private String password="1234";

    public String getSecretBase64(){
        String ConcatSecret = getUsername()+":"+getPassword();
        return "Basic "+Base64.encodeToString(ConcatSecret.getBytes(),Base64.NO_WRAP);
    }
}
