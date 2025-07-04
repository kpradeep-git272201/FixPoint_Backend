package com.fixpoint.module.master.repositories;

import com.fixpoint.module.master.entities.UserRole;
import com.fixpoint.module.tracker.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRolesRepo extends JpaRepository<UserRole, Long> {

    @Query(value = "select count(role_code) FROM tracker.user_roles where role_code = 'ROLE_ADMIN'", nativeQuery = true)
    int fadminCount();
}
