package br.com.zupacademy.gabriel.ecommerce.products;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gabriel.ecommerce.category.Category;
import br.com.zupacademy.gabriel.ecommerce.category.CategoryRepository;
import br.com.zupacademy.gabriel.ecommerce.config.validations.ExistsId;
import br.com.zupacademy.gabriel.ecommerce.products.characteristics.CharacteristicsRequest;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

public class ProductRequest {
	
	@NotBlank
	private String name;
	@Positive @NotNull
	private BigDecimal price;
	@Min(value = 0) @NotNull
	private Integer quantity;
	@NotBlank
	@Length(max = 1000)
	private String description;
	@NotNull
	@ExistsId(domainClass = Category.class, fieldName = "id")
	private Long categoryId;
	@Size(min = 3)
	private Set<CharacteristicsRequest> characteristics = new HashSet<>();
	
	public ProductRequest(@NotBlank String name, @Positive @NotNull BigDecimal price, @DecimalMin("0") @NotNull Integer quantity,
			@NotBlank @Length(max = 1000) String description, @NotNull Long categoryId,
			@Size(min = 3) Set<CharacteristicsRequest> characteristics) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.categoryId = categoryId;
		this.characteristics = characteristics;
	}

	public Product toModel(CategoryRepository categoryRepository, User user) {
		Optional<Category> category = categoryRepository.findById(this.categoryId);
		 return new Product(this.name, this.price, this.quantity, this.description, category.get(), user, characteristics);
	}

}
