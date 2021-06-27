package com.project.nando.climbmountain.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
@CrossOrigin
public class HelloWorldController {

	@GetMapping("/")
	public String helloWorld() {
		return "Hello World";
	}
}
