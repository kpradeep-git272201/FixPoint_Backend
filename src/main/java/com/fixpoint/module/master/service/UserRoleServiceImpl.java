package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.DesignationDto;
import com.fixpoint.module.master.Dtos.UserRolesDtos;
import com.fixpoint.module.master.entities.Designation;
import com.fixpoint.module.master.entities.UserRole;
import com.fixpoint.module.master.repositories.UserRolesRepo;
import com.fixpoint.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private CustomObjectMapper objectMapper;
    @Autowired
    private UserRolesRepo userRoleRepository;
    @Override
    public List<UserRolesDtos> getUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        return Arrays.asList(objectMapper.convert(userRoles, UserRolesDtos[].class));
    }

    @Override
    public UserRolesDtos addUserRoles(UserRolesDtos userRolesDtos) {
        UserRole userRole = objectMapper.convert(userRolesDtos, UserRole.class);
        userRole.setCreatedDate(LocalDateTime.now());
        userRole.setUpdatedDate(LocalDateTime.now());
        UserRole role = userRoleRepository.save(userRole);
        return objectMapper.convert(role, UserRolesDtos.class);
    }

    @Override
    public int getAdminCount() {
        return userRoleRepository.fadminCount();
    }
}
