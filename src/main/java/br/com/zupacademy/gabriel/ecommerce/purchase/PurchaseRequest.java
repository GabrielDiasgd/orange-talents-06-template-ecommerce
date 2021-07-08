package br.com.zupacademy.gabriel.ecommerce.purchase;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.gabriel.ecommerce.config.validations.ExistsId;
import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.purchase.enums.Gateway;

public class PurchaseRequest {
	
	@Positive @NotNull
	private Integer quantity;
	@NotNull
	@ExistsId(domainClass = Product.class, fieldName = "id")
	private Long productId;
	@NotNull @Enumerated
	private Gateway gateway;
	
	

	public PurchaseRequest(@Positive @NotNull Integer quantity, @NotNull Long productId,Gateway gateway) {
		super();
		this.quantity = quantity;
		this.productId = productId;
		this.gateway = gateway;
	}

	public Long getProductId() {
		return productId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public Gateway getGateway() {
		return gateway;
	}

	
	

}
