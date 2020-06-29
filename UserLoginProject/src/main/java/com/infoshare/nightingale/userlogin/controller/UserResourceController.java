package com.infoshare.nightingale.userlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infoshare.nightingale.userlogin.model.AuthenticationRequest;
import com.infoshare.nightingale.userlogin.model.AuthenticationResponse;
import com.infoshare.nightingale.userlogin.model.User;
import com.infoshare.nightingale.userlogin.model.UserDetailsDto;
import com.infoshare.nightingale.userlogin.services.MyUserDetailsService;
import com.infoshare.nightingale.userlogin.util.JwtUtil;


@RestController
public class UserResourceController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping( "/hello" )
	public String firstPage() {
		return "Hello World";
	}

	
	  @PostMapping("/register")
	  public ResponseEntity<User>  postUser(@RequestBody UserDetailsDto userDto)
	  { 
		  User user;
	  	  user = userDetailsService.save(userDto); 
	  	  if(user==null) return new  ResponseEntity<User>(HttpStatus.NOT_FOUND);
	  	  
	  	   return new ResponseEntity<User>(HttpStatus.OK);
	  }
	  
	
	@PostMapping( "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt).getJwt());
	}

}
