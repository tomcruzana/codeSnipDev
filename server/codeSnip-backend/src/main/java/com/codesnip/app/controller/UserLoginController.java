package com.codesnip.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesnip.app.dto.UserDto;
import com.codesnip.app.service.UserService;

@RestController
public class UserLoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public UserDto getUserProfile(Authentication authentication) {
		UserDto userDto = userService.readByEmail(authentication.getName());
		return userDto;
		// todo convert to response entity
	}
}
