package br.com.zupacademy.gabriel.ecommerce.transactions;

import javax.validation.constraints.NotNull;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

public class PagseguroReturnRequest implements CreateTransaction {

	@NotNull
	private Long systemPaymentId;
	@NotNull
	private TransactionStatus status;
	
	public PagseguroReturnRequest(@NotNull Long systemPaymentId, @NotNull TransactionStatus status) {
		super();
		this.systemPaymentId = systemPaymentId;
		this.status = status;
	}
	public Long getSystemPaymentId() {
		return systemPaymentId;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "PaypalReturnRequest [systemPurchaseId=" + systemPaymentId
				+ ", statusPayment=" + status + "]";
	}


	@Override
	public Transaction createTransaction(Purchase purchase) {
		return new Transaction(this.systemPaymentId, status, purchase);
	}
}
