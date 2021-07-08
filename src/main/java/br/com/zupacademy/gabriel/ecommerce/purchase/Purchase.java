package br.com.zupacademy.gabriel.ecommerce.purchase;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.purchase.enums.Gateway;
import br.com.zupacademy.gabriel.ecommerce.purchase.enums.StatusPurchase;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@Entity
public class Purchase {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Positive
	private Integer quantity;
	@Positive
	private BigDecimal price;
	@Enumerated(EnumType.STRING)
	private StatusPurchase status = StatusPurchase.STARTED;
	@Enumerated(EnumType.STRING)
	private Gateway gatewayPurchase;
	@ManyToOne
	private Product product;
	@ManyToOne
	private User buyer;
	
	public Purchase(Integer quantity, Product product, User buyer, Gateway gatewayPurchase) {
		super();
		this.quantity = quantity;
		this.price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
		this.product = product;
		this.buyer = buyer;
		this.gatewayPurchase = gatewayPurchase;
	}
	
	public Long getId() {
		return id;
	}
	
	public Gateway getGatewayPurchase() {
		return gatewayPurchase;
	}
	
	public User getBuyer() {
		return buyer;
	}
	
	public Product getProduct() {
		return product;
	}
	public Integer getQuantity() {
		return quantity;
	}

	public String generateGateway(UriComponentsBuilder uriComponentsBuilder) {
		return this.gatewayPurchase.getGateway().generateGatewayUrl(this, uriComponentsBuilder);
	}
	


}
