package com.fixpoint.module.master.repositories;

import com.fixpoint.module.master.entities.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssusTypeRepo extends JpaRepository<IssueType, Long> {
}
