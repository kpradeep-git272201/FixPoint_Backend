package com.fixpoint.module.tracker.service;

import com.fixpoint.module.tracker.dtos.IssueDTO;
import com.fixpoint.module.tracker.dtos.IssueResponseDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IssueService {
    boolean createIssue(IssueDTO issueDTO);
    IssueDTO getIssueById(Long id);
    IssueResponseDTO updateIssue(Long id, IssueDTO issueDTO);
    boolean deleteIssue(Long id);
    List<IssueResponseDTO> getAllIssues();

    Map<String, Long> getIsueByStatus();
    byte[] generateIssuesDocx(Map<String, String> data) throws IOException;
}
