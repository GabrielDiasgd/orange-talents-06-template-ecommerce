package br.com.zupacademy.gabriel.ecommerce.products.question;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@Entity
public class Question {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String title;
	@NotNull @ManyToOne
	private User user;
	@NotNull @ManyToOne
	private Product product;
	@NotNull
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@Deprecated
	public Question() {
	}
	
	public Question(@NotBlank String title, @NotNull User user, @NotNull Product product) {
		super();
		this.title = title;
		this.user = user;
		this.product = product;
	}
	
	

}
