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
    private String username;
    private String designation;
    private String email;
    private char isActive;
    private Set<String> roleIds;
    private Integer tPin;

}
