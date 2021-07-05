package br.com.zupacademy.gabriel.ecommerce.products;

import javax.validation.constraints.NotBlank;

public class CharacteristicsRequest {
	
	@NotBlank
	private String name;
	@NotBlank
	private String description;
	
	public CharacteristicsRequest(@NotBlank String name, @NotBlank String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public Characteristic toModel (Product product) {
		return new Characteristic(this.name, this.description, product);
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CharacteristicsRequest other = (CharacteristicsRequest) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	

}
