package br.com.zupacademy.gabriel.ecommerce.purchase;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.purchase.enums.Gateway;
import br.com.zupacademy.gabriel.ecommerce.purchase.enums.StatusPurchase;
import br.com.zupacademy.gabriel.ecommerce.transactions.CreateTransaction;
import br.com.zupacademy.gabriel.ecommerce.transactions.Transaction;
import br.com.zupacademy.gabriel.ecommerce.transactions.TransactionStatus;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;
import io.jsonwebtoken.lang.Assert;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@OneToMany(mappedBy = "purchase", cascade = CascadeType.MERGE)
	private Set<Transaction> transactions = new HashSet<>();

	@Deprecated
	public Purchase() {
	}

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
	public BigDecimal getPrice() {
		return price;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public String generateGateway(UriComponentsBuilder uriComponentsBuilder) {
		return this.gatewayPurchase.getGateway().generateGatewayUrl(this, uriComponentsBuilder);
	}


	public void processTransaction(CreateTransaction request) {
		Transaction transaction = request.createTransaction(this);
	Assert.isTrue(!this.transactions.contains(transaction), "Já existe uma transação com esse id de gateway de pagamento");
	Assert.isTrue(!hasSuccessfulTransaction(), "Já tem uma transação com sucesso para essa compra");
	if (transaction.getStatus().equals(TransactionStatus.SUCESSO)) {
		this.status = StatusPurchase.FINISHED;
		this.transactions.add(transaction);
	}else {
		this.transactions.add(transaction);
	}
	}

	public boolean hasSuccessfulTransaction() {
		return this.transactions.stream()
				.anyMatch(transaction -> transaction.getStatus().equals(TransactionStatus.SUCESSO));
	}
	
	public Transaction findSuccessfulTrasactional() {
		List<Transaction> transactionSuccessful = this.transactions.stream()
				.filter(t -> t.getStatus().equals(TransactionStatus.SUCESSO)).collect(Collectors.toList());
				Assert.isTrue(!transactionSuccessful.isEmpty(), "Algo de errado aconteceu e não tem nenhuma compra com sucesso");
		 return transactionSuccessful.get(0);
	}


}
