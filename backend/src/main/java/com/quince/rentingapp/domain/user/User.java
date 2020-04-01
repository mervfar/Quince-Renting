package com.quince.rentingapp.domain.user;

import com.quince.rentingapp.domain.BaseEntity;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "UserSeq")
@Table(name = "Users")
public class User extends BaseEntity  {


    private String username;
    private String password;
    private String email;
    private String imageUrl;
    private int TCno;



}
