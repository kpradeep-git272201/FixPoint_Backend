package com.fixpoint.module.master.controller;

import com.fixpoint.module.master.Dtos.DesignationDto;
import com.fixpoint.module.master.Dtos.IssueStatusDtos;
import com.fixpoint.module.master.Dtos.IssueTypeDtos;
import com.fixpoint.module.master.Dtos.UserRolesDtos;
import com.fixpoint.module.master.service.DesignationService;


import com.fixpoint.module.master.service.IssueStatusService;
import com.fixpoint.module.master.service.IssueTypeService;
import com.fixpoint.module.master.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/issueType")
    public ResponseEntity<Object> addIssueType(@RequestBody IssueTypeDtos issueTypeDtos){
        IssueTypeDtos issueType = issueTypeService.addIssueType(issueTypeDtos);
        return new ResponseEntity<>(issueType, HttpStatus.OK);
    }
    @GetMapping("/issueStatus")
    public ResponseEntity<Object> getIssueStatus(){
        List<IssueStatusDtos> issueStatus = issueStatusService.getIssueStatus();
        return new ResponseEntity<>(issueStatus, HttpStatus.OK);
    }
    @PostMapping("/issueStatus")
    public ResponseEntity<Object> addIssueStatus(@RequestBody IssueStatusDtos issueStatusDtos){
        IssueStatusDtos issueStatus = issueStatusService.addIssueStatus(issueStatusDtos);
        return new ResponseEntity<>(issueStatus, HttpStatus.OK);
    }
    @GetMapping("/userRoles")
    public ResponseEntity<Object> getUserRoles(){
        List<UserRolesDtos> userRoles = userRoleService.getUserRoles();
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }
    @PostMapping("/userRoles")
    public ResponseEntity<Object> addUserRoles(@RequestBody UserRolesDtos userRolesDtos){
        UserRolesDtos userRoles = userRoleService.addUserRoles(userRolesDtos);
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }
    @GetMapping("/designation")
    public ResponseEntity<Object> getDesignation(){
        List<DesignationDto> designation = designationService.getDesignation();
        return new ResponseEntity<>(designation, HttpStatus.OK);
    }
    @PostMapping("/designation")
    public ResponseEntity<Object> addDesignation(@RequestBody DesignationDto designationDto){
        DesignationDto designation = designationService.addDesignation(designationDto);
        return new ResponseEntity<>(designation, HttpStatus.OK);
    }
    @GetMapping("/admin-count")
    public ResponseEntity<Object> getAdminCount(){
        int count = userRoleService.getAdminCount();
        Map<String, Object> response = new HashMap<>();
        if(count>=1){
            response.put("message" , "Admin already created");
            response.put("count", count);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            response.put("message" , "Admin not created");
            response.put("count", count);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

}
