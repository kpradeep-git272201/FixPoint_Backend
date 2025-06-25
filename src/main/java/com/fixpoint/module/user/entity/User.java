package com.fixpoint.module.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
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
    private String userName;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "is_active")
    private char isActive;

    @ElementCollection
    @CollectionTable(
            name = "user_role_ids",
            schema = "tracker",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role_id")
    private Set<Long> roleIds;

}
