package com.sway3i.repository;

import com.sway3i.entities.Course;
import com.sway3i.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCreatedBy(User user);
}
