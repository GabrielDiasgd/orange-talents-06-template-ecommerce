package br.com.zupacademy.gabriel.ecommerce.products.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Min(1) @Max(5) @NotNull
	private Integer rating;
	@NotBlank
	private String title;
	@NotBlank @Length(max = 500)
	private String description;
	@NotNull @ManyToOne
	private User user;
	@NotNull @ManyToOne
	private Product product;
	
	
	
	public String getDescription() {
		return description;
	}

	public Integer getRating() {
		return rating;
	}

	public String getTitle() {
		return title;
	}

	public User getUser() {
		return user;
	}

	public Product getProduct() {
		return product;
	}

	@Deprecated
	public Review() {	
	}
	
	public Review(@Min(1) @Max(5) Integer rating, @NotBlank String title,
			@NotBlank @Length(max = 500) String description, @NotNull Product product, @NotNull User user) {
		super();
		this.rating = rating;
		this.title = title;
		this.description = description;
		this.user = user;
		this.product = product;
	}
	
	

}
