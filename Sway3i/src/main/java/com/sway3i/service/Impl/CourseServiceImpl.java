package com.sway3i.service.Impl;

import com.sway3i.dto.Course.Request.CourseRequestDTO;
import com.sway3i.dto.Course.Response.CourseDetailsResponseDTO;
import com.sway3i.dto.Course.Response.CourseResponseDTO;
import com.sway3i.dto.Course.Response.CourseWithDetailsResponseDTO;
import com.sway3i.dto.Utils.ProgramDTO;
import com.sway3i.dto.Utils.UserDTO;
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
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final FeesRepository feesRepository;

//    @Override
//    public List<CourseResponseDTO> getAllCourses() {
//        List<Course> courses = courseRepository.findAll();
//        return courses.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<CourseWithDetailsResponseDTO> getAllCoursesByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        List<Course> courses = courseRepository.findByCreatedBy(user);
        return courses.stream()
                .map(this::convertToDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDetailsResponseDTO getCourseDetails(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        return CourseDetailsResponseDTO.builder()
                .price(course.getPrice())
                .fees(course.getFees())
                .build();
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
        // Retrieve the programs associated with the course
        List<Long> programIds = courseRequest.getProgramIds();
        List<Program> programs = new ArrayList<>();
        if (programIds != null && !programIds.isEmpty()) {
            for (Long programId : programIds) {
                Program program = programRepository.findById(programId)
                        .orElseThrow(() -> new RuntimeException("Program not found with id: " + programId));
                programs.add(program);
            }
        }

        // Retrieve the fixed set of fees based on their IDs
        List<Fees> fixedFees = feesRepository.findAllById(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));

        // Create a new course entity with the retrieved programs and fixed fees
        Course course = Course.builder()
                .createdAt(LocalDate.now())
                .createdBy(userRepository.getUserById(courseRequest.getCreatedByUserId()))
                .subject(courseRequest.getSubject())
                .courseName(courseRequest.getCourseName())
                .courseDetails(courseRequest.getCourseDetails())
                .courseIsFor(courseRequest.getCourseIsFor())
                .price(courseRequest.getPrice())
                .city(courseRequest.getCity())
                .educationLevel(courseRequest.getEducationLevel())
                .type(courseRequest.getType())
                .maxStudents(courseRequest.getMaxStudents())
                .programs(programs)
                .fees(fixedFees)
                .link(courseRequest.getLink())
                .address(courseRequest.getAddress())
                .build();

        return course;
    }

    private CourseResponseDTO convertToDTO(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .createdAt(LocalDate.now())
                .createdByUserId(userRepository.getUserById(course.getCreatedBy().getId()).getId())
                .subject(course.getSubject())
                .courseName(course.getCourseName())
                .courseDetails(course.getCourseDetails())
                .courseIsFor(course.getCourseIsFor())
                .price(course.getPrice())
                .city(course.getCity())
                .educationLevel(course.getEducationLevel().name())
                .type(course.getType().name())
                .maxStudents(course.getMaxStudents())
                .programIds(course.getPrograms().stream()
                        .map(Program::getId)
                        .collect(Collectors.toList()))
                .link(course.getLink())
                .address(course.getAddress())
                .build();
    }



    //Updated getAllCourses method to return CourseWithDetailsResponseDTO

    @Override
    public List<CourseWithDetailsResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseWithDetailsResponseDTO> getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(this::convertToDetailsDTO);
    }


    private CourseWithDetailsResponseDTO convertToDetailsDTO(Course course) {
        return CourseWithDetailsResponseDTO.builder()
                .id(course.getId())
                .createdAt(course.getCreatedAt())
                .createdByUser(convertToUserDTO(course.getCreatedBy()))
                .courseName(course.getCourseName())
                .subject(course.getSubject())
                .courseDetails(course.getCourseDetails())
                .courseIsFor(course.getCourseIsFor())
                .price(course.getPrice())
                .city(course.getCity())
                .educationLevel(course.getEducationLevel().name())
                .type(course.getType().name())
                .maxStudents(course.getMaxStudents())
                .programs(convertToProgramDTOList(course.getPrograms()))
                .link(course.getLink())
                .address(course.getAddress())
                .build();
    }

    private UserDTO convertToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    private List<ProgramDTO> convertToProgramDTOList(List<Program> programs) {
        return programs.stream()
                .map(program -> ProgramDTO.builder()
                        .id(program.getId())
                        .date(program.getDay())
                        .time(program.getTime())
                        .build())
                .collect(Collectors.toList());
    }

}
