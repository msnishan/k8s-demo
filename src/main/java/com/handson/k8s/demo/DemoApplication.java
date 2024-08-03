package com.handson.k8s.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

	private final Map<String, Student> studentMap = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@PostMapping("/student")
	public Student createStudent(@RequestBody Student student) {
		studentMap.put(student.id, student);
		return student;
	}

	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable String id) {
		return studentMap.get(id);
	}

	public record Student(String id, String name) {}

}
