package com.fixpoint.module.tracker.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;




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
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date startDate;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date endDate;

        private String description;
        private String remarks;

        private String createdBy;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private LocalDateTime createdDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private LocalDateTime updatedDate;
        private String updatedBy;

        private MultipartFile attachment;

        public IssueDTO(){}

        public IssueDTO(Long id, String projectCode, String title, String requester, String status, String assignTo, String type, Date startDate, Date endDate, String description, String remarks, String createdBy, LocalDateTime createdDate, LocalDateTime updatedDate, String updatedBy, MultipartFile attachment) {
                this.id = id;
                this.projectCode = projectCode;
                this.title = title;
                this.requester = requester;
                this.status = status;
                this.assignTo = assignTo;
                this.type = type;
                this.startDate = startDate;
                this.endDate = endDate;
                this.description = description;
                this.remarks = remarks;
                this.createdBy = createdBy;
                this.createdDate = createdDate;
                this.updatedDate = updatedDate;
                this.updatedBy = updatedBy;
                this.attachment = attachment;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getProjectCode() {
                return projectCode;
        }

        public void setProjectCode(String projectCode) {
                this.projectCode = projectCode;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getRequester() {
                return requester;
        }

        public void setRequester(String requester) {
                this.requester = requester;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public String getAssignTo() {
                return assignTo;
        }

        public void setAssignTo(String assignTo) {
                this.assignTo = assignTo;
        }

        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public Date getStartDate() {
                return startDate;
        }

        public void setStartDate(Date startDate) {
                this.startDate = startDate;
        }

        public Date getEndDate() {
                return endDate;
        }

        public void setEndDate(Date endDate) {
                this.endDate = endDate;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getRemarks() {
                return remarks;
        }

        public void setRemarks(String remarks) {
                this.remarks = remarks;
        }

        public String getCreatedBy() {
                return createdBy;
        }

        public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
        }

        public LocalDateTime getCreatedDate() {
                return createdDate;
        }

        public void setCreatedDate(LocalDateTime createdDate) {
                this.createdDate = createdDate;
        }

        public LocalDateTime getUpdatedDate() {
                return updatedDate;
        }

        public void setUpdatedDate(LocalDateTime updatedDate) {
                this.updatedDate = updatedDate;
        }

        public String getUpdatedBy() {
                return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
                this.updatedBy = updatedBy;
        }

        public MultipartFile getAttachment() {
                return attachment;
        }

        public void setAttachment(MultipartFile attachment) {
                this.attachment = attachment;
        }
}