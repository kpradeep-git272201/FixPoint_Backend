package com.fixpoint.module.master.Dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesignationDto {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Boolean isActive;
}
