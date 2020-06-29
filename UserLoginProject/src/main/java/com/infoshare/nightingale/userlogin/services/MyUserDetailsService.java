package com.infoshare.nightingale.userlogin.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.infoshare.nightingale.userlogin.model.Role;
import com.infoshare.nightingale.userlogin.model.User;
import com.infoshare.nightingale.userlogin.model.UserDetailsDto;
import com.infoshare.nightingale.userlogin.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserService {

	@Autowired
	private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
    {
    	 	
        User user = userRepository.findByEmail(email);
  
        if (user == null)
        {
           throw new UsernameNotFoundException("Invalid username or password.");
        }
         org.springframework.security.core.userdetails.User userDetails=  new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
   
        return userDetails;
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
    {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
	@Override
	public com.infoshare.nightingale.userlogin.model.User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public com.infoshare.nightingale.userlogin.model.User save(UserDetailsDto registration) {
		
		User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(registration.getPassword()));
        Collection<Role> collRole=new ArrayList<Role>();
        registration.getRoles().forEach(role->collRole.add(new Role(role)));
         user.setRoles(collRole);
        
        try {
			user =userRepository.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw e;
		}
       return user;
	}
    
}