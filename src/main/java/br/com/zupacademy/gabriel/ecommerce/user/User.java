package br.com.zupacademy.gabriel.ecommerce.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Email
	private String login;
	@NotBlank
	@Length(min = 6)
	private String password;
	@NotNull
	@PastOrPresent
	private LocalDateTime creationDate = LocalDateTime.now();
	
	/**
	 * 
	 * @param login string no formato de email
	 * @param password senha deve vir em texto limpo
	 */
	public User(@NotBlank @Email String login, @NotBlank @Length(min = 6) String password) {
		super();
		this.login = login;
		this.password = new BCryptPasswordEncoder().encode(password);
		
	}
	


}
