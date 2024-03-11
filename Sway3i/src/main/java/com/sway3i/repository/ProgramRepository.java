package com.sway3i.repository;

import com.sway3i.entities.Program;
import com.sway3i.entities.enums.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    boolean existsByDayAndTime(Days day, LocalTime time);

}
