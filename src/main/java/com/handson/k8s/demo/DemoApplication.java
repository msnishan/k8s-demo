package com.handson.k8s.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

	private final Map<String, Student> studentMap = new HashMap<>();

	RestTemplate restTemplate = new RestTemplate();

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
		ResponseEntity<Address> address =
				restTemplate.getForEntity("http://client-service/address/" + id, Address.class);
		Student s = studentMap.get(id);
		return new Student(s.id, s.name, address.getBody());
	}

	public record Student(String id, String name, Address address) {}

	public record Address(String stdId, String city, String country) {}

}
