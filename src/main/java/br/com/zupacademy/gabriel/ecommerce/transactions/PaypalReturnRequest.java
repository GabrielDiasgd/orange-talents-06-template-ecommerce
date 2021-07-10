package br.com.zupacademy.gabriel.ecommerce.transactions;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

public class PaypalReturnRequest implements CreateTransaction {
	
	@NotNull
	private Long systemPaymentId;
	@NotNull @Min(0) @Max(1)
	private Long status;
	

	public PaypalReturnRequest(@NotNull Long systemPaymentId, @NotNull @Min(0) @Max(1) Long status) {
		super();
		this.systemPaymentId = systemPaymentId;
		this.status = status;
	}

	public Long getSystemPaymentId() {
		return systemPaymentId;
	}

	public Long getStatus() {
		return status;
	}

	@Override
	public Transaction createTransaction(Purchase purchase) {
		TransactionStatus convertedStatus = this.status == 0 ? TransactionStatus.ERRO : TransactionStatus.SUCESSO;
		return new Transaction(this.systemPaymentId, convertedStatus, purchase);
	}
	


}
