package com.springrest.SpringRest.service;

import com.springrest.SpringRest.dao.RoleDAO;
import com.springrest.SpringRest.dao.StudentDAO;
import com.springrest.SpringRest.entity.Role;
import com.springrest.SpringRest.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service @Transactional
public class StudentServiceImp implements StudentService, UserDetailsService {

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentServiceImp(){
    }
    @Override
    public Student addStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentDAO.save(student);
        return student;
    }

    @Override
    public Role addRole(Role role) {
        System.out.println("Saving new Role");
        return roleDAO.save(role);
    }

    @Override
    public void addRoleToStudent(String name, String roleName) {
        Student student = studentDAO.findByName(name);
        Role role = roleDAO.findByName(roleName);
        student.getRoles().add(role);
    }

    @Override
    public List<Student> getStudents() {
        return studentDAO.findAll();
    }

    @Override
    public Student getStudent(String name) {
        return studentDAO.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Student student = studentDAO.findByName(name);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        student.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(student.getName(),student.getPassword(), authorities);
    }
}
