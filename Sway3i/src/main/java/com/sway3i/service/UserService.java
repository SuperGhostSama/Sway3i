package com.sway3i.service;

import com.sway3i.entities.Role;
import com.sway3i.entities.User;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public interface UserService {
    Optional<User> getById(Long id);

    Role grantRoleToUser(Long userId, Long roleId);


}
