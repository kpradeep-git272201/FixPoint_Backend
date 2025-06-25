package com.fixpoint.module.tracker.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueDTO {

        private Long id;
        private String projectCode;
        private String title;
        private String requester;
        private String status;
        private String assignTo;
        private String type;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date startDate;

        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date endDate;

        private String description;
        private String remarks;

        private String createdBy;
        private Date createdDate;
        private Date updatedDate;
        private String updatedBy;

        private MultipartFile attachment;
}