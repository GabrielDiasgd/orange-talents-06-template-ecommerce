package br.com.zupacademy.gabriel.ecommerce.user.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.zupacademy.gabriel.ecommerce.config.validations.UniqueValue;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

public class UserRequest {
	
	@NotBlank
	@Email
	@UniqueValue(domainClass = User.class, fieldName = "login")
	private String login;
	@NotBlank
	@Length(min = 6)
	private String password;
	
	
	public UserRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String password) {
		super();
		this.login = login;
		this.password = password;
	}


	public User toModel() {
		return new User(this.login, this.password);
	}


	public UsernamePasswordAuthenticationToken toLogin() {
		return new UsernamePasswordAuthenticationToken(this.login, this.password);
	}
	
	
	
	

}
