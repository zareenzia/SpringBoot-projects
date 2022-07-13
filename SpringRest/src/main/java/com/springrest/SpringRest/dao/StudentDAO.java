package com.springrest.SpringRest.dao;

import com.springrest.SpringRest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDAO extends JpaRepository<Student, Long> {
    Student findByName(String name);
}
