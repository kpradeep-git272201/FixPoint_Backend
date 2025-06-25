package com.fixpoint.module.tracker.service;

import com.fixpoint.module.tracker.dtos.IssueDTO;
import com.fixpoint.module.tracker.entity.Issue;

import java.util.List;

public interface IssueService {
    IssueDTO createIssue(IssueDTO issueDTO);

    IssueDTO getIssueById(Long id);

    IssueDTO updateIssue(Long id, IssueDTO issueDTO);

    boolean deleteIssue(Long id);

    List<Issue> getAllIssues();
}
