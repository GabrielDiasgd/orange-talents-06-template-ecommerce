package br.com.zupacademy.gabriel.ecommerce.transactions;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

public interface CreateTransaction {
	
	public Transaction createTransaction (Purchase purchase);

}
