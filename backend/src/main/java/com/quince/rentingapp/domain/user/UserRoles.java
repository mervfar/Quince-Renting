package com.quince.rentingapp.domain.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoles  implements GrantedAuthority {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private UserRole role;

    @Override
    public String getAuthority() {
        return this.roleId.toString();
    }
}
