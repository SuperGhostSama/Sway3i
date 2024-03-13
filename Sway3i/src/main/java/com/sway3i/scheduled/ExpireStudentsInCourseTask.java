package com.sway3i.scheduled;

import com.sway3i.dto.StudentsInCourse.Response.StudentsInCourseResponseDTO;
import com.sway3i.repository.StudentsInCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.sway3i.entities.StudentsInCourse;
import com.sway3i.entities.enums.PricingPlan;
import com.sway3i.service.StudentsInCourseService;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
public class ExpireStudentsInCourseTask {


    private final StudentsInCourseRepository studentsInCourseRepository;


    // Run this task every day at midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void expireStudentsInCourse() {
        List<StudentsInCourse> studentsInCourseList = studentsInCourseRepository.findAll();

        studentsInCourseList.forEach(studentsInCourse -> {
            if (shouldExpire(studentsInCourse)) {
                studentsInCourse.setExpired(true);
                studentsInCourseRepository.save(studentsInCourse);
            }
        });
    }


    private boolean shouldExpire(StudentsInCourse studentsInCourse) {
        LocalDate expirationDate = calculateExpirationDate(studentsInCourse);
        return LocalDate.now().isAfter(expirationDate);
    }

    private LocalDate calculateExpirationDate(StudentsInCourse studentsInCourse) {
        PricingPlan pricingPlan = studentsInCourse.getPricingPlan();
        LocalDate createdAt = studentsInCourse.getCreatedAt();

        switch (pricingPlan) {
            case ONE_MONTH:
                return createdAt.plusDays(30);
            case SIX_MONTHS:
                return createdAt.plusMonths(6);
            case TEN_MONTHS:
                return createdAt.plusMonths(10);
            default:
                throw new IllegalArgumentException("Invalid pricing plan: " + pricingPlan);
        }
    }
}
