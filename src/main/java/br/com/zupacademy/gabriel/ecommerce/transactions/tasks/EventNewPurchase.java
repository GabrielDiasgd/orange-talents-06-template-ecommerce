package br.com.zupacademy.gabriel.ecommerce.transactions.tasks;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

public interface EventNewPurchase {

	
	public void notifyInvoice(Purchase purchase);
	public void notifySellerRanking(Purchase purchase);
	public void sendEmailBuyer(Purchase purchase);
	public void processesUnfinishedPurchaseTasks(Purchase purchase, UriComponentsBuilder uri);
}
