package com.jidnivai.springdom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jidnivai.springdom.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    
}
