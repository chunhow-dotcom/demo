package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private static AtomicLong counter = new AtomicLong();
	
	// define greeting component variable without initialization
	private GreetingComponent g;
	
	// inject/initialize GreetingComponet in constructor
	@Autowired
	public GreetingController(GreetingComponent g) {
		this.g = g;
	}
	
	//test the GreetingComponent
	@GetMapping("/testgreeting")
	public ResponseEntity<String> getMessage() {
		return ResponseEntity.ok(g.getMessage());
	}
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template,name));
	}
	
	@GetMapping("/add/{num1}/{num2}")
	public Integer addTwoNumbers(@PathVariable (value = "num1") Integer num1, 
	@PathVariable (value = "num2") Integer num2) {
		return num1 + num2;
	}
	
	@GetMapping("/sub/{num1}/{num2}")
	public Integer subTwoNumbers(@PathVariable (value = "num1") Integer num1, 
	@PathVariable (value = "num2") Integer num2) {
		return num1 - num2;
	}
}
