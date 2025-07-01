package com.fixpoint.module.tracker.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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

        @Column(name = "start_date", nullable = false)
        private LocalDate startDate;

        @Column(name = "end_date")
        private LocalDate endDate;

        private String description;
        private String remarks;

        private String createdBy;
        private String updatedBy;

        private MultipartFile attachment;

        public IssueDTO(){}

        public IssueDTO(Long id, String projectCode, String title, String requester, String status, String assignTo, String type, LocalDate startDate, LocalDate endDate, String description, String remarks, String createdBy, String updatedBy, MultipartFile attachment) {
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

        public LocalDate getStartDate() {
                return startDate;
        }

        public void setStartDate(LocalDate startDate) {
                this.startDate = startDate;
        }

        public LocalDate getEndDate() {
                return endDate;
        }

        public void setEndDate(LocalDate endDate) {
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