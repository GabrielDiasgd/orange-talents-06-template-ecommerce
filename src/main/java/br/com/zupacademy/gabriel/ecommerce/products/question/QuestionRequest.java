package br.com.zupacademy.gabriel.ecommerce.products.question;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

public class QuestionRequest {
	
	@JsonProperty
	@NotBlank
	private String title;
	
	@Deprecated
	public QuestionRequest() {
	}

	public QuestionRequest(@NotBlank String title) {
		super();
		this.title = title;
	}
	
	public Question toModel (User user, Product product) {
		return new Question(this.title, user, product);
	}

}
