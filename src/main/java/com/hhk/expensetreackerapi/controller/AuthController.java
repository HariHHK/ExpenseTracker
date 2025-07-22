package com.hhk.expensetreackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hhk.expensetreackerapi.entity.AuthModel;
import com.hhk.expensetreackerapi.entity.JwtResponse;
import com.hhk.expensetreackerapi.entity.User;
import com.hhk.expensetreackerapi.entity.UserModel;
import com.hhk.expensetreackerapi.security.CustomUserDetailsService;
import com.hhk.expensetreackerapi.service.IUserService;
import com.hhk.expensetreackerapi.util.JwtTokenUtil;

import jakarta.validation.Valid;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService userService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception {

		authenticate(authModel.getEmail(), authModel.getPassword());

		// we need to generate jwt token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("User disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials");
		}

	}

	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
}
