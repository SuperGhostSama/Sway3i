package com.sway3i.repository;

import com.sway3i.entities.Course;
import com.sway3i.entities.StudentsInCourse;
import com.sway3i.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsInCourseRepository extends JpaRepository<StudentsInCourse, Long> {

    boolean existsByStudentAndCourse(User student, Course course);

    List<StudentsInCourse> findByStudentId(Long studentId);
    @Query("SELECT sic.course FROM StudentsInCourse sic WHERE sic.student.id = :userId AND sic.course.id = :courseId")
    Optional<Course> isStudentEnrolled(Long userId, Long courseId);
}
