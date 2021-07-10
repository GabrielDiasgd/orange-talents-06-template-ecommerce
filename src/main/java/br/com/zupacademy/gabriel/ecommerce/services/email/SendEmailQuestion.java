package br.com.zupacademy.gabriel.ecommerce.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.gabriel.ecommerce.products.question.Question;

@Component
public class SendEmailQuestion {
	
	@Autowired
	private EmailSender email;
		
	public void forNewQuestion(Question question) {
		email.send(question.getUser().getUsername(),
				question.getProduct().getOwner().getUsername(), "Nova Pergunta para o produto: " + question.getProduct().getName(), question.getTitle());
		
	}
}


