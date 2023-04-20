package com.sg.leo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	// localhost:8080
	// localhost:8080/
	@GetMapping({"", "/"})
	public String getPostList() {
		return "index";
	}

}
