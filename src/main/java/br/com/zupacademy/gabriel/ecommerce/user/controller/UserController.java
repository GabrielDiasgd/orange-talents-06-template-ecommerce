package br.com.zupacademy.gabriel.ecommerce.user.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gabriel.ecommerce.user.model.User;
import br.com.zupacademy.gabriel.ecommerce.user.repository.UserRepository;
import br.com.zupacademy.gabriel.ecommerce.user.request.UserRequest;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create (@RequestBody @Valid UserRequest userRequest) {
		User user = userRequest.toModel();
		userRepository.save(user);
		
		return ResponseEntity.ok().build();
	}

}
