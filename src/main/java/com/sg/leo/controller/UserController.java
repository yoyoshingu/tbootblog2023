package com.sg.leo.controller;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.leo.domain.RoleType;
import com.sg.leo.domain.User;
import com.sg.leo.exception.TBlogException;
import com.sg.leo.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return user.getUsername() + "회원 가입성공";
	}
	
	@GetMapping("/user/get/{id}")
	public @ResponseBody User getUser(@PathVariable int id) {
		User findUser = userRepository.findById(id).orElseThrow(new Supplier<TBlogException>() {
			public TBlogException get() {
				return new TBlogException(id + "회원없음");
			}
		});
		return findUser;
	}

}
