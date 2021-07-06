package br.com.zupacademy.gabriel.ecommerce.products.review;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

public class ReviewRequest {
	
	@Min(1) @Max(5)
	private Integer rating;
	@NotBlank
	private String title;
	@NotBlank @Length(max = 500)
	private String description;
	
	
	public ReviewRequest(@Min(1) @Max(5) Integer rating, @NotBlank String title,
			@NotBlank @Length(max = 500) String description) {
		super();
		this.rating = rating;
		this.title = title;
		this.description = description;

	}
	 public Review toModel(Product product, User user) {
		 return new Review(this.rating, this.title, this.description, product, user);
	 }
}

