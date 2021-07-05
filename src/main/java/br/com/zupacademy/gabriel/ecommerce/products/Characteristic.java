package br.com.zupacademy.gabriel.ecommerce.products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Characteristic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	@NotNull
	@ManyToOne
	private Product product;

	@Deprecated
	public Characteristic() {
	}

	public Characteristic(@NotBlank String name, @NotBlank String description, @NotNull Product product) {
		this.name = name;
		this.description = description;
		this.product = product;
	}

}
