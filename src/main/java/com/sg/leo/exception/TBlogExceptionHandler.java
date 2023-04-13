package com.sg.leo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class TBlogExceptionHandler {
	@ExceptionHandler(value=Exception.class)
	public String globalExceptionHandler(Exception e) {
		return "<h1>아이쿠, 예외발생ㄷ, Tblogexceptionhandler</h1>"
				+ "<h2>" + e.getMessage() + "</h2>";
	}
}
