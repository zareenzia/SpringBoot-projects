package com.springrest.SpringRest;

import com.springrest.SpringRest.entity.Role;
import com.springrest.SpringRest.entity.Student;
import com.springrest.SpringRest.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(StudentService studentService){
		return  args -> {
			studentService.addRole(new Role(null,"ROLE_STUDENT"));
			studentService.addRole(new Role(null,"ROLE_ADMIN"));

			studentService.addStudent(new Student(null,"Zareen","abcd","Mohakhali",31, new ArrayList<>()));
			studentService.addRoleToStudent("Zareen","ROLE_ADMIN");

			studentService.addStudent(new Student(null,"Admin","abcd","Uttara",34, new ArrayList<>()));
			studentService.addRoleToStudent("Admin","ROLE_ADMIN");

			studentService.addStudent(new Student(null,"Nipa","abcd","Badda",35, new ArrayList<>()));
			studentService.addRoleToStudent("Nipa","ROLE_STUDENT");

			studentService.addStudent(new Student(null,"Rana","abcd","Dhaka",23, new ArrayList<>()));
			studentService.addRoleToStudent("Rana","ROLE_STUDENT");
		};
	}
}
