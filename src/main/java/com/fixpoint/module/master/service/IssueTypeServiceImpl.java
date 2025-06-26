package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.IssueTypeDtos;
import com.fixpoint.module.master.entities.IssueType;
import com.fixpoint.module.master.repositories.IssusTypeRepo;
import com.fixpoint.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IssueTypeServiceImpl implements IssueTypeService{
    @Autowired
    private CustomObjectMapper objectMapper;
    @Autowired
    private IssusTypeRepo issueTypeRepository;
    @Override
    public List<IssueTypeDtos> getIssueType() {
        List<IssueType> issueTypes = issueTypeRepository.findAll();
        return Arrays.asList(objectMapper.convert(issueTypes, IssueTypeDtos[].class));
    }
}
