package com.fixpoint.module.master.controller;

<<<<<<< HEAD
import com.fixpoint.module.master.Dtos.DesignationDto;
import com.fixpoint.module.master.Dtos.IssueStatusDtos;
import com.fixpoint.module.master.Dtos.IssueTypeDtos;
import com.fixpoint.module.master.Dtos.UserRolesDtos;
import com.fixpoint.module.master.service.DesignationService;
=======
import com.fixpoint.module.master.Dtos.IssueStatusDtos;
import com.fixpoint.module.master.Dtos.IssueTypeDtos;
import com.fixpoint.module.master.Dtos.UserRolesDtos;
>>>>>>> origin/main
import com.fixpoint.module.master.service.IssueStatusService;
import com.fixpoint.module.master.service.IssueTypeService;
import com.fixpoint.module.master.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/master")
public class MasterController {

    @Autowired
    private IssueStatusService issueStatusService;
    @Autowired
    private IssueTypeService issueTypeService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private DesignationService designationService;

    @GetMapping("/issueType")
    public ResponseEntity<Object> getIssueType(){
        List<IssueTypeDtos> issueType = issueTypeService.getIssueType();
        return new ResponseEntity<>(issueType, HttpStatus.OK);
    }
    @GetMapping("/issueStatus")
    public ResponseEntity<Object> getIssueStatus(){
        List<IssueStatusDtos> issueStatus = issueStatusService.getIssueStatus();
        return new ResponseEntity<>(issueStatus, HttpStatus.OK);
    }

    @GetMapping("/userRoles")
    public ResponseEntity<Object> getUserRoles(){
        List<UserRolesDtos> userRoles = userRoleService.getUserRoles();
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }
<<<<<<< HEAD

    @GetMapping("/designation")
    public ResponseEntity<Object> getDesignation(){
        List<DesignationDto> designation = designationService.getDesignation();
        return new ResponseEntity<>(designation, HttpStatus.OK);
    }
=======
>>>>>>> origin/main
}
