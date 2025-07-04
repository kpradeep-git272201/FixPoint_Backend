package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.IssueTypeDtos;

import java.util.List;

public interface IssueTypeService {
    public List<IssueTypeDtos> getIssueType();
    public IssueTypeDtos addIssueType(IssueTypeDtos issueTypeDtos);
}
