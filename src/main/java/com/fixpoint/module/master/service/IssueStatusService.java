package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.IssueStatusDtos;
import com.fixpoint.module.master.entities.IssueStatus;

import java.util.List;

public interface IssueStatusService {
    public List<IssueStatusDtos> getIssueStatus();

    public IssueStatusDtos addIssueStatus(IssueStatusDtos issueStatusDtos);
}
