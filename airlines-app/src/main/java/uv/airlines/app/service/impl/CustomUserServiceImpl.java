package uv.airlines.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uv.airlines.app.domain.CustomUserDetails;
import uv.airlines.app.domain.User;
import uv.airlines.app.repository.UserRepository;

@Service
public class CustomUserServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user =  userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Username not found") );
		return user.map(CustomUserDetails::new).get();
		
	}

}
