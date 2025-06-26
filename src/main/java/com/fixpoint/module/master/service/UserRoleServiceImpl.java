package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.UserRolesDtos;
import com.fixpoint.module.master.entities.UserRole;
import com.fixpoint.module.master.repositories.UserRolesRepo;
import com.fixpoint.utils.CustomObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
