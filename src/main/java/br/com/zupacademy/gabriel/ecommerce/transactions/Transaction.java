package br.com.zupacademy.gabriel.ecommerce.transactions;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long systemPaymentId;
	@NotNull
	private LocalDateTime creationDate = LocalDateTime.now();
	@NotNull @Enumerated(EnumType.STRING)
	private TransactionStatus status;
	@NotNull @ManyToOne
	private Purchase purchase;
	
	@Deprecated
	public Transaction() {
	}
	
	public Transaction(@NotNull Long systemPaymentId, @NotNull TransactionStatus status, @NotNull Purchase purchase) {
		super();
		this.systemPaymentId = systemPaymentId;
		this.status = status;
		this.purchase = purchase;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public Long getId() {
		return id;
	}
	public Long getSystemPaymentId() {
		return systemPaymentId;
	}

	public Purchase getPurchase() {
		return purchase;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((systemPaymentId == null) ? 0 : systemPaymentId.hashCode());
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
		Transaction other = (Transaction) obj;
		if (systemPaymentId == null) {
			if (other.systemPaymentId != null)
				return false;
		} else if (!systemPaymentId.equals(other.systemPaymentId))
			return false;
		return true;
	}


	
	

}
