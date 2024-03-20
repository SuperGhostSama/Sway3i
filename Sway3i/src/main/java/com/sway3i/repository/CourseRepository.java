package com.sway3i.repository;

import com.sway3i.entities.Course;
import com.sway3i.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCreatedBy(User user);

    @Query("SELECT c FROM Course c ORDER BY c.createdAt DESC")
    List<Course> find4LatestCourses();
}
