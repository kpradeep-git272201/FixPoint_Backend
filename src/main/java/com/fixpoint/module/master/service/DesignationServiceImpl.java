package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.DesignationDto;
import com.fixpoint.module.master.Dtos.IssueStatusDtos;
import com.fixpoint.module.master.entities.Designation;
import com.fixpoint.module.master.entities.IssueStatus;
import com.fixpoint.module.master.repositories.DesignationRepo;
import com.fixpoint.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DesignationServiceImpl implements DesignationService {
    @Autowired
    private DesignationRepo designationRepo;
    @Autowired
    private CustomObjectMapper objectMapper;
    @Override
    public List<DesignationDto> getDesignation() {
        List<Designation> designations = designationRepo.findAll();
        return Arrays.asList(objectMapper.convert(designations, DesignationDto[].class));
    }

    @Override
    public DesignationDto addDesignation(DesignationDto designationDto) {
        Designation convert = objectMapper.convert(designationDto, Designation.class);
        Designation designation = designationRepo.save(convert);
        return objectMapper.convert(designation, DesignationDto.class);
    }
}
