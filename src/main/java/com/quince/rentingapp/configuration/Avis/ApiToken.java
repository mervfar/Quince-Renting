package com.quince.rentingapp.configuration.Avis;
import static java.lang.Integer.parseInt;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiToken  implements Serializable {

    private String access_token;

    private String token_type;

    private String expires_in;

    private long  timeOnSet;

    public String getBearer(){
        return "Bearer "+getAccess_token();
    }
    public Boolean isExpired(){
        System.out.println("TimeStamp NOW   >>>"+(System.currentTimeMillis() / 1000L));
        System.out.println("TimeStamp TOKEN >>>"+(getTimeOnSet()));
        return ((System.currentTimeMillis() / 1000L)-getTimeOnSet()) > 7100;
    }

}
