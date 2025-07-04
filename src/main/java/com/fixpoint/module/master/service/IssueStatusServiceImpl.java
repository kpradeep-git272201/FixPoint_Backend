package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.IssueStatusDtos;
import com.fixpoint.module.master.Dtos.IssueTypeDtos;
import com.fixpoint.module.master.entities.IssueStatus;
import com.fixpoint.module.master.entities.IssueType;
import com.fixpoint.module.master.repositories.IssueStatusRepo;
import com.fixpoint.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueStatusServiceImpl implements IssueStatusService{
    @Autowired
    private CustomObjectMapper objectMapper;
    @Autowired
    private IssueStatusRepo issueStatusrepository;
    @Override
    public List<IssueStatusDtos> getIssueStatus() {
        List<IssueStatus> issueStatus = issueStatusrepository.findAll();
        return Arrays.asList(objectMapper.convert(issueStatus, IssueStatusDtos[].class));
    }

    @Override
    public IssueStatusDtos addIssueStatus(IssueStatusDtos issueStatusDtos) {
        IssueStatus issueStatus = objectMapper.convert(issueStatusDtos, IssueStatus.class);
        IssueStatus save = issueStatusrepository.save(issueStatus);
        return objectMapper.convert(save, IssueStatusDtos.class);
    }
}
