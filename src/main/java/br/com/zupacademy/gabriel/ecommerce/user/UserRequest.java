package br.com.zupacademy.gabriel.ecommerce.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gabriel.ecommerce.config.validations.UniqueValue;

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
	
	
	
	

}
