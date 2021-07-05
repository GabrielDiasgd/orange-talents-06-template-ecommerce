package br.com.zupacademy.gabriel.ecommerce.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.gabriel.ecommerce.user.model.User;
import br.com.zupacademy.gabriel.ecommerce.user.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByLogin(username);
		if (user.isPresent()) {
			return user.get();
		}
		throw new UsernameNotFoundException("Dados Inválidos");
	}

}
