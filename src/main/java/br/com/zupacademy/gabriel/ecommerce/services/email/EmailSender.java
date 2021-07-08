package br.com.zupacademy.gabriel.ecommerce.services.email;

import br.com.zupacademy.gabriel.ecommerce.products.question.Question;
import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

public interface EmailSender {

	void send(Question question);
	
	void send(Purchase purchase);

}
