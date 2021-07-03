package br.com.zupacademy.gabriel.ecommerce.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@ManyToOne
	private Category motherCategory;
	
	@Deprecated
	public Category() {
	}

	public Category(@NotBlank String name) {
		this.name = name;
	}
	

	public void setMotherCategory(Category category) {
		this.motherCategory = category;
	}
	
	

}
