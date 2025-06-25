package com.fixpoint.module.tracker.controller;

import com.fixpoint.module.tracker.dtos.IssueDTO;
import com.fixpoint.module.tracker.entity.Issue;
import com.fixpoint.module.tracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;
    @PostMapping(value = "/addIssue", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createIssue(
            @RequestPart("issue") IssueDTO issueDTO,
            @RequestPart(value = "attachment", required = false) MultipartFile attachment
    ) {
        issueDTO.setAttachment(attachment);

        IssueDTO created = issueService.createIssue(issueDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        List<Issue> list = issueService.getAllIssues();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueDTO> getIssue(@PathVariable Long id) {
        IssueDTO dto = issueService.getIssueById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssueDTO> updateIssue(@PathVariable Long id, @ModelAttribute IssueDTO issueDTO) {
        IssueDTO updated = issueService.updateIssue(id, issueDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable Long id) {
        boolean deleted = issueService.deleteIssue(id);
        return deleted ?
               new ResponseEntity<>("Issue deleted successfully", HttpStatus.OK) :
                ResponseEntity.status(500).body("Failed to delete issue");
    }

    @DeleteMapping("/batch")
    public ResponseEntity<String> deleteMultipleIssues(@ModelAttribute List<Long> ids) {
        return ResponseEntity.status(501).body("Not implemented yet");
    }

    @PutMapping("/batch")
    public ResponseEntity<String> updateMultipleIssues(@ModelAttribute List<IssueDTO> issues) {
        return ResponseEntity.status(501).body("Not implemented yet");
    }

    @GetMapping("/test")
    public String getTest(){
        return "I am running";
    }
}
