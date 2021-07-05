package br.com.zupacademy.gabriel.ecommerce.products;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.gabriel.ecommerce.category.Category;
import br.com.zupacademy.gabriel.ecommerce.products.characteristics.Characteristic;
import br.com.zupacademy.gabriel.ecommerce.products.characteristics.CharacteristicsRequest;
import br.com.zupacademy.gabriel.ecommerce.products.image.ProductImage;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@Positive @NotNull
	private BigDecimal price;
	@DecimalMin("0") @NotNull
	private Integer quantity;
	@NotBlank
	@Length(max = 1000)
	private String description;
	@PastOrPresent
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@Size(min = 3) @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
	private Set<Characteristic> characteristics = new HashSet<>();
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
	private Set<ProductImage> images = new HashSet<>(); 
	
	@NotNull @ManyToOne
	private Category category;
	
	@NotNull @ManyToOne
	private User owner;
	
	@Deprecated
	public Product() {
	}

	public Product(@NotBlank String name, @Min(1) @NotNull BigDecimal price, @DecimalMin("0") @NotNull Integer quantity,
			@NotBlank @Length(max = 1000) String description, @NotNull Category category, @NotNull User user,
			@Size(min = 3) Set<CharacteristicsRequest> characteristics) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.category = category;
		this.owner = user;
		this.characteristics.addAll(characteristics.stream().map(c -> c.toModel(this)).collect(Collectors.toSet()));
	}

	public boolean belongUser(User user) {
		return this.owner.equals(user);
	}

	public void setImages(Set<String> links) {
		links.forEach(link -> this.images.add(new ProductImage(link, this)));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
}
