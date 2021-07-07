package br.com.zupacademy.gabriel.ecommerce.products.characteristics;

public class CharacteristicResponse {

	private String name;
	private String description;
	
	public CharacteristicResponse(Characteristic characteristic) {
		this.name = characteristic.getName();
		this.description = characteristic.getDescription();
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	
}
