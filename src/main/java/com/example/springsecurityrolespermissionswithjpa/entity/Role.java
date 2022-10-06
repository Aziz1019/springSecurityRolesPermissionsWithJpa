package com.example.springsecurityrolespermissionswithjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role extends BasicEntity {
    @Column(nullable = false, unique = true)
    private String roleName;
    @ManyToMany
    @JoinColumn
    public Set<Permission> permissions;
}
