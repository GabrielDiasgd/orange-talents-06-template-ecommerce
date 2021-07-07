package br.com.zupacademy.gabriel.ecommerce.services.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zupacademy.gabriel.ecommerce.products.question.Question;

@Component
@Primary
public class FakeEmailSender implements EmailSender {

	@Override
	public void send(Question question) {
		System.out.println("O usuário " + question.getUser().getUsername() + " enviou a seguinte pergunta: " 
				+ question.getTitle() + " a respeito do produto " + question.getProduct().getName());
	}


}
