package com.sway3i.seeders;

import com.sway3i.entities.Authority;
import com.sway3i.entities.Role;
import com.sway3i.entities.enums.AuthorityEnum;
import com.sway3i.repository.AuthorityRepository;
import com.sway3i.repository.RoleRepository;
import com.sway3i.service.AuthorityService;
import com.sway3i.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleService roleService;
    private final AuthorityService authorityService;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            seedRoles();
        }
    }

    private void seedRoles() {

        // USER MANAGEMENT AUTHORITY
        Authority grantAuthorityToRole = authorityService.getByName(AuthorityEnum.GRANT_AUTHORITY_TO_ROLE.toString())
                .orElseThrow(() -> new RuntimeException("GRANT_AUTHORITY_TO_ROLE authority not found"));

        Authority revokeAuthorityFromRole = authorityService.getByName(AuthorityEnum.REVOKE_AUTHORITY_FROM_ROLE.toString())
                .orElseThrow(() -> new RuntimeException("REVOKE_AUTHORITY_FROM_ROLE authority not found"));

        Authority assignRoleToUser = authorityService.getByName(AuthorityEnum.ASSIGN_ROLE_TO_USER.toString())
                .orElseThrow(() -> new RuntimeException("ASSIGN_ROLE_TO_USER authority not found"));

        Authority createRole = authorityService.getByName(AuthorityEnum.CREATE_ROLE.toString())
                .orElseThrow(() -> new RuntimeException("CREATE_ROLE authority not found"));

        Authority updateRole = authorityService.getByName(AuthorityEnum.UPDATE_ROLE.toString())
                .orElseThrow(() -> new RuntimeException("UPDATE_ROLE authority not found"));

        Authority deleteRole = authorityService.getByName(AuthorityEnum.DELETE_ROLE.toString())
                .orElseThrow(() -> new RuntimeException("DELETE_ROLE authority not found"));

        Authority viewRoles = authorityService.getByName(AuthorityEnum.VIEW_ROLES.toString())
                .orElseThrow(() -> new RuntimeException("VIEW_ROLES authority not found"));

        // USER AUTHORITY
        Authority updateUsers = authorityService.getByName(AuthorityEnum.UPDATE_USERS.toString())
                .orElseThrow(() -> new RuntimeException("UPDATE_USERS authority not found"));

        Authority viewUsers = authorityService.getByName(AuthorityEnum.VIEW_USERS.toString())
                .orElseThrow(() -> new RuntimeException("VIEW_USERS authority not found"));

        Authority deleteUsers = authorityService.getByName(AuthorityEnum.DELETE_USERS.toString())
                .orElseThrow(() -> new RuntimeException("DELETE_USERS authority not found"));

        Authority viewAuthorities = authorityService.getByName(AuthorityEnum.VIEW_AUTHORITIES.toString())
                .orElseThrow(() -> new RuntimeException("VIEW_AUTHORITIES authority not found"));

        // COURSE AUTHORITY
        Authority viewCourses = authorityService.getByName(AuthorityEnum.VIEW_COURSES.toString())
                .orElseThrow(() -> new RuntimeException("VIEW_COURSES authority not found"));

        Authority viewOneCourse = authorityService.getByName(AuthorityEnum.VIEW_ONE_COURSE.toString())
                .orElseThrow(() -> new RuntimeException("VIEW_ONE_COURSE authority not found"));

        Authority createCourse = authorityService.getByName(AuthorityEnum.CREATE_COURSE.toString())
                .orElseThrow(() -> new RuntimeException("CREATE_COURSE authority not found"));

        Authority updateCourse = authorityService.getByName(AuthorityEnum.UPDATE_COURSE.toString())
                .orElseThrow(() -> new RuntimeException("UPDATE_COURSE authority not found"));

        Authority deleteCourse = authorityService.getByName(AuthorityEnum.DELETE_COURSE.toString())
                .orElseThrow(() -> new RuntimeException("DELETE_COURSE authority not found"));




        // Create roles and associate authorities
        Role studentRole = Role.builder()
                .name("ROLE_STUDENT")
                .authorities(Arrays.asList(viewCourses, viewOneCourse))
                .isDefault(true)
                .build();

        Role teacherRole = Role.builder()
                .name("ROLE_TEACHER")
                .authorities(Arrays.asList(
                        viewCourses, viewOneCourse, createCourse, updateCourse, deleteCourse))
                .build();

        Role adminRole = Role.builder()
                .name("ROLE_ADMIN")
                .authorities(authorityRepository.findAll())
                .build();

        roleService.save(studentRole, true);
        roleService.save(teacherRole, true);
        roleService.save(adminRole, true);
    }

}
