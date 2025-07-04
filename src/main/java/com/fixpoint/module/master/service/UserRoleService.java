package com.fixpoint.module.master.service;

import com.fixpoint.module.master.Dtos.UserRolesDtos;

import java.util.List;

public interface UserRoleService {
    public List<UserRolesDtos> getUserRoles();
    public UserRolesDtos addUserRoles(UserRolesDtos userRolesDtos);
    public int getAdminCount();
}
