package com.fixpoint.module.master.repositories;

import com.fixpoint.module.master.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepo extends JpaRepository<UserRole, Long> {
}
