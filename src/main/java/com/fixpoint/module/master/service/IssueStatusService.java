package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.IssueStatusDtos;

import java.util.List;

public interface IssueStatusService {
    public List<IssueStatusDtos> getIssueStatus();
}
