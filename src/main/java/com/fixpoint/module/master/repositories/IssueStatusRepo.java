package com.fixpoint.module.master.repositories;

import com.fixpoint.module.master.entities.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueStatusRepo extends JpaRepository<IssueStatus, Long> {
}
