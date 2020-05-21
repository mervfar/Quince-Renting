package com.quince.rentingapp.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quince.rentingapp.domain.BaseEntity;
import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import com.quince.rentingapp.domain.driverLicense.LicenseCategory;
import com.quince.rentingapp.domain.invoice.Invoice;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "UserSeq")
@Table(name = "Users")
public class User extends BaseEntity implements UserDetails, Serializable {


    private String username;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String imageUrl;
    private String phoneNumber;
    @JsonBackReference
    @Transient
    private MultipartFile imageFile;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "license_id", referencedColumnName = "id")
    private DriverLicense driverLicense;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Invoice> invoiceList;


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
