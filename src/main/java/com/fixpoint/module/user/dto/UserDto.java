package com.fixpoint.module.user.dto;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String userName;
    private String designation;
    private String email;
    private char isActive;
    private Set<Long> roleIds;
}
