package com.sg.leo.controller;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sg.leo.domain.RoleType;
import com.sg.leo.domain.User;
import com.sg.leo.dto.ResponseDTO;
import com.sg.leo.exception.TBlogException;
import com.sg.leo.repository.UserRepository;
import com.sg.leo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return user.toString() + "회원 가입성공";
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
	
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestBody User user) {
		User findUser = userRepository.findById(user.getId()).orElseThrow(()->{
			return new TBlogException(user.getId() + "회원없음");
		});
		findUser.setUsername(user.getUsername());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		userRepository.save(findUser);
		return findUser.toString() + "회원수정성공했음"  ;
	}
	
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return id + "번 회원 삭제 성공";
	}
	
	@GetMapping("/user/list")
	public @ResponseBody List<User> getUserList(){
		return userRepository.findAll();
	}
	
	@GetMapping("/user/page/{page}")
	public @ResponseBody Page <User> getUserListPaging(@PathVariable int page){
		Pageable pageable = 
				PageRequest.of(page, 3, Sort.Direction.DESC, "id", "username");
		return userRepository.findAll(pageable);
	}
	
	@GetMapping("/user/page")
	public @ResponseBody Page<User> getUserListPage(@PageableDefault(page=0, 
	    size=3, direction=Sort.Direction.DESC, sort= {"id", "username"}) Pageable pageable){
		
		return userRepository.findAll(pageable);
	}
	
	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "insertUser";
	}
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/login")
	public @ResponseBody ResponseDTO<?> login(@RequestBody User user ) {
		User findUser = userService.getUser(user.getUsername());
		if(findUser.getUsername() != null) {
			// 패스워드 비교
			if(user.getPassword().equals(findUser.getPassword())) {
				// 로그인 성공
				return new ResponseDTO<>(HttpStatus.OK.value(),
						user.getUsername() + "님 로그인 성공");
			}{
				return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
						user.getUsername() + "님 비밀번호 다름");
			}
		}else {
			// username  없음, 회원가입 필요
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
					user.getUsername() + "님 아이디없음");
		}
	}
}
