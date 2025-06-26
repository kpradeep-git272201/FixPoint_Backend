package com.fixpoint.module.master.Dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueTypeDtos {
    private String name;
    private String code;
    private String description;
    private Boolean isActive;
    private Long createdBy;
    private Long updatedBy;
}

