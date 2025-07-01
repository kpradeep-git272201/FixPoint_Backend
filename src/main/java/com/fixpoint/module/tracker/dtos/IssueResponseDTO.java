package com.fixpoint.module.tracker.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueResponseDTO {

    private Long id;
    private String projectCode;
    private String title;
    private String requester;
    private String status;
    private String assignTo;
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String description;
    private String remarks;

    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String updatedBy;

    private String attachmentBase64;
}