package br.com.zupacademy.gabriel.ecommerce.services.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zupacademy.gabriel.ecommerce.products.question.Question;

@Component
@Primary
public class FakeEmailSender implements EmailSender {

	/*
	 * Aqui ficaria a lógica para o envio de um email de verdade
	 * porém estou apenas simulando o envio e imprimindo no console
	 */
	@Override
	public void sendEmail(Question question) {
		System.out.println("Enviando o e-mail da pergunta" + question.getClass().getFields()) ;
		
	}

}
