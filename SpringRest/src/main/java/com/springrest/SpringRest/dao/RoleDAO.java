package com.springrest.SpringRest.dao;

import com.springrest.SpringRest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
