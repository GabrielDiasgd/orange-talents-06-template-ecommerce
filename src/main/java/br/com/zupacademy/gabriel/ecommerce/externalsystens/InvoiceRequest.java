package br.com.zupacademy.gabriel.ecommerce.externalsystens;

import javax.validation.constraints.NotNull;

public class InvoiceRequest {
	
	@NotNull
	private Long purchaseId;
	@NotNull
	private Long buyerId;
	
	public InvoiceRequest(@NotNull Long purchaseId, @NotNull Long buyerId) {
		super();
		this.purchaseId = purchaseId;
		this.buyerId = buyerId;
	}

	@Override
	public String toString() {
		return "InvoiceRequest [purchaseId=" + purchaseId + ", buyerId=" + buyerId + "]";
	}
	
	
	

}
