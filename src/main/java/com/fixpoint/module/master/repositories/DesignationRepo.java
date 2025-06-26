package com.fixpoint.module.master.repositories;

import com.fixpoint.module.master.entities.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {
}
