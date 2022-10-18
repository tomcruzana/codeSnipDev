package com.codesnip.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codesnip.app.dto.UserDto;
import com.codesnip.app.entity.Authority;
import com.codesnip.app.entity.User;
import com.codesnip.app.repository.AuthorityRepository;
import com.codesnip.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		// persist new customer data
		User newUser = new User();

		// hash the password using bcrypt!
		String hashedPassword = passwordEncoder.encode(userDto.getPassword());
		userDto.setPassword(hashedPassword);

		// register current date
		userDto.setDateCreated(new Date(System.currentTimeMillis()));

		// transfer customer data
		newUser.setUsername(userDto.getUsername());
		newUser.setEmail(userDto.getEmail());
		newUser.setPassword(userDto.getPassword());
		newUser.setDateCreated(userDto.getDateCreated());
		newUser.setEnabled(true);
		userRepository.save(newUser);

		// initialize and save default role
		Authority authority = new Authority();
		authority.setName("ROLE_FREE_USER");
		authority.setUser(newUser);
		authorityRepository.save(authority);

		return userDto;
	}

	@Override
	public UserDto readByEmail(String email) {
		UserDto userDto;
		List<User> users = userRepository.findByEmail(email);

		if (users.size() > 0) {
			// convert and return customer to customerDto type
			userDto = new UserDto(users.get(0));

			return userDto;
		}

		// return null if no customer is found!
		return null;
	}

}
