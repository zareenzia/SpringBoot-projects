package com.springrest.SpringRest.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.SpringRest.entity.Role;
import com.springrest.SpringRest.entity.Student;
import com.springrest.SpringRest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/controller")
public class MyController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>>getStudents(){
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @PostMapping ("/students/save")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/controller/student/save").toUriString());
        return ResponseEntity.created(uri).body(studentService.addStudent(student));
    }


    @PostMapping ("/role/save")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/controller/role/save").toUriString());
        return ResponseEntity.created(uri).body(studentService.addRole(role));
    }

    @PostMapping ("/role/addtostudent")
    public ResponseEntity<?> addRoleToStudent(@RequestBody RoleToStudentForm form){
        studentService.addRoleToStudent(form.getName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping ("/token/refresh")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {

                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Student student = studentService.getStudent(username);

                String access_token = JWT.create()
                        .withSubject(student.getName())
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",student.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);


                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch (Exception exception){
                response.setHeader("error", exception.getMessage());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

class RoleToStudentForm{
    private String name;
    private String roleName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

