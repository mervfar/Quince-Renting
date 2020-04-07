package com.quince.rentingapp.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.quince.rentingapp.domain.BaseEntity;
import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "UserSeq")
@Table(name = "Users")
public class User extends BaseEntity implements UserDetails, Serializable {


    private String username;
    private String password;
    private String email;
    private String imageUrl;
    private long TCno;
    private int birthDate;
    @JsonBackReference
    @Transient
    private MultipartFile imageFile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "license_id", referencedColumnName = "id")
    private DriverLicense driverLicense;


    private Role userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
