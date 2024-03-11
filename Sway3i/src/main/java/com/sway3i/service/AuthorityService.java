package com.sway3i.service;

import com.sway3i.entities.Authority;
import com.sway3i.entities.enums.AuthorityEnum;

import java.util.List;
import java.util.Optional;

public interface AuthorityService {
    List<Authority> getAllByName(List<AuthorityEnum> authorities);
    Optional<Authority> getByName(String authorityEnum);

    Optional<Authority> getById(Long id);
}

