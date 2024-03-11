package com.sway3i.repository;

import com.sway3i.entities.TeacherDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDemandRepository extends JpaRepository<TeacherDemand, Long> {

}
