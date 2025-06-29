package com.fixpoint.module.tracker.repository;

import com.fixpoint.module.tracker.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findAllByOrderByUpdatedDateDesc();

}
