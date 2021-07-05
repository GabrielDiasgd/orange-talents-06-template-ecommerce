package br.com.zupacademy.gabriel.ecommerce.products.image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gabriel.ecommerce.products.Product;

@Entity
public class ProductImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String link;
	
	@NotNull
	@ManyToOne
	private Product product;
	
	@Deprecated
	public ProductImage() {
	}

	public ProductImage(@NotBlank String link, @NotNull Product product) {
		super();
		this.link = link;
		this.product = product;
	}
	
	
	

}
