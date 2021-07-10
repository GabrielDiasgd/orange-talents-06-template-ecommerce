package br.com.zupacademy.gabriel.ecommerce.transactions.tasks;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;
import br.com.zupacademy.gabriel.ecommerce.services.email.SendEmailPurchase;

@Service
public class FinishPurchaseTasks implements EventNewPurchase {
	
	@Autowired
	private SendEmailPurchase sendEmailPurchase;

	public void processCompletedPurchaseTasks(Purchase purchase) {
		notifyInvoice(purchase);
		notifySellerRanking(purchase);
		sendEmailBuyer(purchase);
		
	}
	
	@Override
	public void processesUnfinishedPurchaseTasks(Purchase purchase, UriComponentsBuilder uri) {
		String uriGenerated = purchase.generateGateway(uri);
		sendEmailPurchase.purchaseUnfinished(purchase, uriGenerated);
	}
	

	@Override
	public void sendEmailBuyer(Purchase purchase) {
		sendEmailPurchase.purchaseCompletedSuccessfully(purchase);
	}

	@Override
	public void notifyInvoice(Purchase purchase) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("purchaseId", purchase.getId(), "buyerId", purchase.getBuyer().getId());
		restTemplate.postForEntity("http://localhost:8080/nota-fiscal", request, String.class);
	}

	@Override
	public void notifySellerRanking(Purchase purchase) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("purchaseId", purchase.getId(), "sellerId", purchase.getProduct().getOwner().getId());
		restTemplate.postForEntity("http://localhost:8080/ranking-vendedores", request, String.class);
	}
	
}
