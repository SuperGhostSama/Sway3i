package com.sway3i.service.Impl;

import com.sway3i.dto.Course.Request.CourseRequestDTO;
import com.sway3i.dto.Course.Response.CourseResponseDTO;
import com.sway3i.entities.Course;
import com.sway3i.entities.Fees;
import com.sway3i.entities.Program;
import com.sway3i.entities.User;
import com.sway3i.repository.CourseRepository;
import com.sway3i.repository.FeesRepository;
import com.sway3i.repository.ProgramRepository;
import com.sway3i.repository.UserRepository;
import com.sway3i.service.CourseService;
import com.sway3i.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final FeesRepository feesRepository;

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseResponseDTO> getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public List<CourseResponseDTO> getAllCoursesByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        List<Course> courses = courseRepository.findByCreatedBy(user);
        return courses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequest) {
        Course course = convertToEntity(courseRequest);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO updatedCourseRequest) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()) {
            Course updatedCourse = convertToEntity(updatedCourseRequest);
            updatedCourse.setId(id);
            Course savedCourse = courseRepository.save(updatedCourse);
            return convertToDTO(savedCourse);
        } else {
            throw new RuntimeException("Course not found with id: " + id);
        }
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private Course convertToEntity(CourseRequestDTO courseRequest) {
        List<Long> programIds = courseRequest.getProgramIds();
        List<Program> programs = new ArrayList<>();

        if (programIds != null && !programIds.isEmpty()) {
            for (Long programId : programIds) {
                Program program = programRepository.findById(programId)
                        .orElseThrow(() -> new RuntimeException("Program not found with id: " + programId));

                programs.add(program);
            }
        }
        //These are the fixed fees
        List<Long> hardcodedFeeIds = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        List<Fees> fees = new ArrayList<>();

        for (Long feeId : hardcodedFeeIds) {
            Fees fee = feesRepository.findById(feeId)
                    .orElseThrow(() -> new RuntimeException("Fee not found with id: " + feeId));

            fees.add(fee);
        }

        return Course.builder()
                .createdAt(LocalDate.now())
                .createdBy(userRepository.getUserById(courseRequest.getCreatedByUserId()))
                .subject(courseRequest.getSubject())
                .courseDetails(courseRequest.getCourseDetails())
                .courseIsFor(courseRequest.getCourseIsFor())
                .price(courseRequest.getPrice())
                .city(courseRequest.getCity())
                .educationLevel(courseRequest.getEducationLevel())
                .type(courseRequest.getType())
                .studentsInPerson(courseRequest.getStudentsInPerson())
                .programs(programs)
                .fees(fees)
                .build();
    }

    private CourseResponseDTO convertToDTO(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .createdAt(course.getCreatedAt())
                .createdByUserId(userRepository.getUserById(course.getCreatedBy().getId()).getId())
                .subject(course.getSubject())
                .courseDetails(course.getCourseDetails())
                .courseIsFor(course.getCourseIsFor())
                .price(course.getPrice())
                .city(course.getCity())
                .educationLevel(course.getEducationLevel().name())
                .type(course.getType().name())
                .studentsInPerson(course.getStudentsInPerson())
                .programIds(course.getPrograms().stream()
                        .map(Program::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
