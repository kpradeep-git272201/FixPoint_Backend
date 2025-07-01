package com.fixpoint.module.tracker.repository;

import com.fixpoint.module.tracker.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findAllByOrderByUpdatedDateDesc();
    @Query(value = "SELECT * FROM tracker.issues WHERE start_date >= :startDate", nativeQuery = true)
    List<Issue> findIssuesFromStartDate(@Param("startDate") LocalDate startDate);
}
