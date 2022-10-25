package com.demo.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/hello")
public class HelloWorldController {

	@GetMapping
	public ResponseEntity<Object> get() {

		Map<String, String> response = new HashMap<>();
		response.put("response", "Hello world!");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Object> get(@PathVariable String name) {

		Map<String, String> response = new HashMap<>();
		response.put("response", "Hello world " + name);

		return ResponseEntity.ok(response);
	}

}
