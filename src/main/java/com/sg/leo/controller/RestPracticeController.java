package com.sg.leo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestPracticeController {

	@RequestMapping("/hello")
	public String hello() {
		return "to /hello response, from RestPracticeController.java";
	}
	
	@GetMapping("/get")
	public String get() {
		return "to /get response from RestPracticeController.java";
	}
	
	@GetMapping("/getchk/{variable}")
	public String getchk(@PathVariable String variable) {
		return "to /getchk reponse, pathvariable:" + variable;
	}
	
	// http://localhost:8080/api/getparam?unit=meter&q=seoul
	@GetMapping("/getparam")
	public String getparam(@RequestParam String unit, 
			@RequestParam String  q) {
		return "to /getparam unit: " + unit + " city: " + q;
	}
	
}
