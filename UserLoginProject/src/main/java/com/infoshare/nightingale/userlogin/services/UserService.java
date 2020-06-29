package com.infoshare.nightingale.userlogin.services;


import org.springframework.security.core.userdetails.UserDetailsService;


import com.infoshare.nightingale.userlogin.model.User;
import com.infoshare.nightingale.userlogin.model.UserDetailsDto;



public interface UserService extends UserDetailsService {

    User findByEmail(String email);

   User save(UserDetailsDto registration);
}
