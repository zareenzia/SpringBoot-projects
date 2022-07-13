package com.springrest.SpringRest.service;

import com.springrest.SpringRest.entity.Role;
import com.springrest.SpringRest.entity.Student;
import com.sun.source.util.SimpleDocTreeVisitor;

import java.util.List;

public interface StudentService {

    public Student addStudent(Student student);
    Role addRole(Role role);
    void addRoleToStudent(String name, String roleName);

    public List<Student> getStudents();
    public Student getStudent(String name);

}
