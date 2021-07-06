package br.com.zupacademy.gabriel.ecommerce.services.email;

import br.com.zupacademy.gabriel.ecommerce.products.question.Question;

public interface EmailSender {
	
	public void sendEmail (Question question);

}
