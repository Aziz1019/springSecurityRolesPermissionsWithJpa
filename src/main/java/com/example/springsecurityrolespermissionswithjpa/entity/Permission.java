package com.example.springsecurityrolespermissionswithjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Permission extends BasicEntity implements GrantedAuthority {
    @Column(unique = true, nullable = false)
    private String permissionName;

    @Override
    public String getAuthority() {
        return this.permissionName;
    }
}
