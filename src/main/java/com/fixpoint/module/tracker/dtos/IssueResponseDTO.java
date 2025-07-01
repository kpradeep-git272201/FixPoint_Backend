package com.fixpoint.module.tracker.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private String description;
    private String remarks;
    private String createdBy;
    private String updatedBy;
    private String attachmentBase64;
}