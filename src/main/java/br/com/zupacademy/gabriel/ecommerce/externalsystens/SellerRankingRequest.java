package br.com.zupacademy.gabriel.ecommerce.externalsystens;

import javax.validation.constraints.NotNull;

public class SellerRankingRequest {

	@NotNull
	private Long purchaseId;
	@NotNull
	private Long sellerId;
	
	public SellerRankingRequest(@NotNull Long purchaseId, @NotNull Long sellerId) {
		super();
		this.purchaseId = purchaseId;
		this.sellerId = sellerId;
	}

	@Override
	public String toString() {
		return "SellerRankingRequest [purchaseId=" + purchaseId + ", sellerId=" + sellerId + "]";
	}
	
	
	
}
