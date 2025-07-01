package com.fixpoint.module.tracker.service;

import com.fixpoint.module.tracker.dtos.IssueDTO;
import com.fixpoint.module.tracker.dtos.IssueResponseDTO;
import com.fixpoint.module.tracker.entity.Issue;
import com.fixpoint.module.tracker.repository.IssueRepository;
import com.fixpoint.module.user.exceptions.ResouceNotFoundException;
import com.fixpoint.utils.CustomObjectMapper;
import com.fixpoint.utils.DocxExportUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements  IssueService{

    @Autowired
    private CustomObjectMapper objectMapper;
    @Autowired
    private IssueRepository issueRepository;
    @Override
    public boolean createIssue(IssueDTO issueDTO) {
        Issue entity = Issue.builder()
                .projectCode(issueDTO.getProjectCode())
                .title(issueDTO.getTitle())
                .requester(issueDTO.getRequester())
                .status(issueDTO.getStatus())
                .assignTo(issueDTO.getAssignTo())
                .type(issueDTO.getType())
                .startDate(issueDTO.getStartDate())
                .endDate(issueDTO.getEndDate())
                .updatedDate(LocalDateTime.now())
                .updatedBy(issueDTO.getUpdatedBy())
                .description(issueDTO.getDescription())
                .remarks(issueDTO.getRemarks())
                .createdBy(issueDTO.getCreatedBy())
                .createdDate(LocalDateTime.now())
                .build();

        MultipartFile file = issueDTO.getAttachment();
        if (file != null && !file.isEmpty()) {
            try {
                entity.setAttachment(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error reading attachment", e);
            }
        }

        Issue saved = issueRepository.save(entity);
        if(!(saved == null)){
            return true;
        }
        return false;

    }

    @Override
    public IssueDTO getIssueById(Long id) {
        Issue issue = this.issueRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Issue not found with ID: " + id));
        return objectMapper.convert(issue, IssueDTO.class);
    }

    @Override
    public IssueResponseDTO updateIssue(Long id, IssueDTO issueDTO) {
        Issue existing = this.issueRepository.findById(id)
                .orElseThrow(() -> new ResouceNotFoundException("Issue not found with ID: " + id));

        if (issueDTO.getProjectCode() != null) {
            existing.setProjectCode(issueDTO.getProjectCode());
        }
        if (issueDTO.getTitle() != null) {
            existing.setTitle(issueDTO.getTitle());
        }
        if (issueDTO.getRequester() != null) {
            existing.setRequester(issueDTO.getRequester());
        }
        if (issueDTO.getStatus() != null) {
            existing.setStatus(issueDTO.getStatus());
        }
        if (issueDTO.getAssignTo() != null) {
            existing.setAssignTo(issueDTO.getAssignTo());
        }
        if (issueDTO.getType() != null) {
            existing.setType(issueDTO.getType());
        }
        if (issueDTO.getStartDate() != null) {
            existing.setStartDate(issueDTO.getStartDate());
        }
        if (issueDTO.getEndDate() != null) {
            existing.setEndDate(issueDTO.getEndDate());
        }
        if (issueDTO.getDescription() != null) {
            existing.setDescription(issueDTO.getDescription());
        }
        if (issueDTO.getRemarks() != null) {
            existing.setRemarks(issueDTO.getRemarks());
        }

        MultipartFile file = issueDTO.getAttachment();
        if (file != null && !file.isEmpty()) {
            try {
                existing.setAttachment(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error while updating attachment", e);
            }
        }

        existing.setUpdatedDate(LocalDateTime.now());
        existing.setUpdatedBy(issueDTO.getUpdatedBy());

        Issue issue = this.issueRepository.save(existing);
        String base64Attachment = null;
        if (issue.getAttachment() != null && issue.getAttachment().length > 0) {
            base64Attachment = Base64.getEncoder().encodeToString(issue.getAttachment());
        }
        return IssueResponseDTO.builder()
                .id(issue.getId())
                .assignTo(issue.getAssignTo())
                .createdBy(issue.getCreatedBy())
                .description(issue.getDescription())
                .endDate(issue.getEndDate())
                .projectCode(issue.getProjectCode())
                .remarks(issue.getRemarks())
                .requester(issue.getRequester())
                .startDate(issue.getStartDate())
                .status(issue.getStatus())
                .title(issue.getTitle())
                .type(issue.getType())
                .updatedBy(issue.getUpdatedBy())
                .attachmentBase64(base64Attachment)
                .build();
    }

    @Override
    public boolean deleteIssue(Long id) {
        try {
            this.issueRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<IssueResponseDTO> getAllIssues() {
        List<Issue> issues = this.issueRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedDate"));
        return issues.stream().map(issue -> {
            String base64Attachment = null;
            if (issue.getAttachment() != null && issue.getAttachment().length > 0) {
                base64Attachment = Base64.getEncoder().encodeToString(issue.getAttachment());
            }
            return IssueResponseDTO.builder()
                    .id(issue.getId())
                    .assignTo(issue.getAssignTo())
                    .createdBy(issue.getCreatedBy())
                    .description(issue.getDescription())
                    .endDate(issue.getEndDate())
                    .projectCode(issue.getProjectCode())
                    .remarks(issue.getRemarks())
                    .requester(issue.getRequester())
                    .startDate(issue.getStartDate())
                    .status(issue.getStatus())
                    .title(issue.getTitle())
                    .type(issue.getType())
                    .updatedBy(issue.getUpdatedBy())
                    .attachmentBase64(base64Attachment)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getIsueByStatus() {
        List<Issue> issues = this.issueRepository.findAll();
        return issues.stream().collect(Collectors.groupingBy(Issue::getStatus, Collectors.counting()));
    }

    @Override
    public byte[] generateIssuesDocx() throws IOException {
        List<Issue> issues = issueRepository.findAll();
        List<IssueResponseDTO> issueList = issues.stream().map(issue -> IssueResponseDTO.builder()
                .id(issue.getId())
                .assignTo(issue.getAssignTo())
                .createdBy(issue.getCreatedBy())
                .description(issue.getDescription())
                .endDate(issue.getEndDate())
                .projectCode(issue.getProjectCode())
                .remarks(issue.getRemarks())
                .requester(issue.getRequester())
                .startDate(issue.getStartDate())
                .status(issue.getStatus())
                .title(issue.getTitle())
                .type(issue.getType())
                .updatedBy(issue.getUpdatedBy())
                .build()
        ).toList();

        return DocxExportUtil.exportMPRDocx(issueList);
    }

}
