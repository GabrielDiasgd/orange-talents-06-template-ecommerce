package br.com.zupacademy.gabriel.ecommerce.category;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zupacademy.gabriel.ecommerce.config.validations.ExistsId;
import br.com.zupacademy.gabriel.ecommerce.config.validations.UniqueValue;

public class CategoryRequest {
	
	@JsonProperty
	@NotBlank
	@UniqueValue(domainClass = Category.class, fieldName = "name")
	private String name;
	@JsonProperty
	@ExistsId(domainClass = Category.class, fieldName = "id")
	private Long motherCategoryId;
	
	public Category toModel(CategoryRepository categoryRepository) {
			Category category = new Category(this.name);
		if (this.motherCategoryId != null) {
			Optional<Category> motherCategory = categoryRepository.findById(this.motherCategoryId);
			Assert.notNull(motherCategory, "Id da categoria deve ser válido");
			category.setMotherCategory(motherCategory.get());
		}
		return category;
	}

}
