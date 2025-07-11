package com.fixpoint.module.tracker.controller;
import org.springframework.http.*;
import com.fixpoint.module.tracker.dtos.IssueDTO;
import com.fixpoint.module.tracker.dtos.IssueResponseDTO;
import com.fixpoint.module.tracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

        boolean issue = issueService.createIssue(issueDTO);
        if(issue){
            return new ResponseEntity<>("Issue added successfully!", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Issue creation failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping
    public ResponseEntity<List<IssueResponseDTO>> getAllIssues() {
        List<IssueResponseDTO> list = issueService.getAllIssues();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/statusCount")
    public ResponseEntity<Object> getIssueByStatus() {
        Map<String, Long> isueByStatus = issueService.getIsueByStatus();
        return new ResponseEntity<>(isueByStatus, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueDTO> getIssue(@PathVariable Long id) {
        IssueDTO dto = issueService.getIssueById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<IssueResponseDTO> updateIssue(@PathVariable Long id, @RequestPart("issue") IssueDTO issueDTO, @RequestPart(value = "attachment", required = false) MultipartFile attachment) {
        issueDTO.setAttachment(attachment);
        IssueResponseDTO updated = issueService.updateIssue(id, issueDTO);
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


    @PostMapping("/download-doc")
    public ResponseEntity<Object> downloadIssuesDoc(@RequestBody Map<String, String> data) throws IOException {

        byte[] docx = issueService.generateIssuesDocx(data);
        //byte[] docx = mprService.generateMonthlyReport(); // using Apache POI
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("MPR-June-2025.docx").build());
        return new ResponseEntity<>(docx, headers, HttpStatus.OK);
    }
}
