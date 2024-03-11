package com.sway3i.repository;

import com.sway3i.entities.StudentsInCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsInCourseRepository extends JpaRepository<StudentsInCourse, Long> {

}
