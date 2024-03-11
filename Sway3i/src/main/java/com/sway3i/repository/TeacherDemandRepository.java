package com.sway3i.repository;

import com.sway3i.entities.TeacherDemand;
import com.sway3i.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDemandRepository extends JpaRepository<TeacherDemand, Long> {
    List<TeacherDemand> findByCreatedBy(User createdBy);

}
