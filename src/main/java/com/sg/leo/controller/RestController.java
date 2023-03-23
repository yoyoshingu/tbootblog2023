package com.sg.leo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sg.leo.domain.User;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@GetMapping("/tblog")
	public User httpGet() {
		User finduser = User.builder().id(1).username("ai").password("222").email("ai@g.com").build();
		return finduser;
	}
	
	@PostMapping("/tblog")
	public String httpPost(@RequestBody User user) {
		return "Post요청 처리" + user.toString();
	}
	
	@PutMapping("/tblog")
	public String httpPut() {
		return "PUT request 처리";
	}
	
	@DeleteMapping("/tblog")
	public String httpDelete() {
		return "Delete 요청처리";
	}
}
