package com.fixpoint.module.tracker.service;

import com.fixpoint.module.tracker.dtos.IssueDTO;
import com.fixpoint.module.tracker.dtos.IssueResponseDTO;
import com.fixpoint.module.tracker.entity.Issue;

import java.io.IOException;
import java.util.List;

public interface IssueService {
    boolean createIssue(IssueDTO issueDTO);
    IssueDTO getIssueById(Long id);
    IssueDTO updateIssue(Long id, IssueDTO issueDTO);
    boolean deleteIssue(Long id);
    List<IssueResponseDTO> getAllIssues();
    byte[] generateIssuesDocx() throws IOException;
}
