package br.com.zupacademy.gabriel.ecommerce.user.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserLoginRequest {


	@NotBlank
	@Email
	private String login;
	@NotBlank
	@Length(min = 6)
	private String password;

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public UserLoginRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String password) {
		this.login = login;
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken toLogin() {
		return new UsernamePasswordAuthenticationToken(this.login, this.password);
		
	}
	
	
}
