package com.fixpoint.module.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = "roleIds")
@Entity
@Table(name = "users", schema = "tracker")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "is_active")
    private char isActive;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_role_ids",
            schema = "tracker",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role_id")
    private Set<String> roleIds = new HashSet<>();

    @Column(name = "t_pin")
    private Long tPin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public char getIsActive() {
        return isActive;
    }
    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }
    public Set<String> getRoleIds() {
        return roleIds;
    }
    public void setRoleIds(Set<String> roleIds) {
        this.roleIds = roleIds;
    }

    public Long gettPin() {
        return tPin;
    }

    public void settPin(Long tPin) {
        this.tPin = tPin;
    }
}
