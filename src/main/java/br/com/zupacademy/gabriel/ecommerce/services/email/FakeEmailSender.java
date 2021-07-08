package br.com.zupacademy.gabriel.ecommerce.services.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.zupacademy.gabriel.ecommerce.products.question.Question;
import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

@Component
@Primary
public class FakeEmailSender implements EmailSender {

	@Override
	public void send(Question question) {
		System.out.println("O usuário " + question.getUser().getUsername() + " enviou a seguinte pergunta: " 
				+ question.getTitle() + " a respeito do produto " + question.getProduct().getName());
	}

	@Override
	public void send(Purchase purchase) {
		System.out.println("O usuário " + purchase.getBuyer().getUsername() + " está querendo comprar " + purchase.getQuantity()
		+ " unidade do produto " + purchase.getProduct().getName() + " através do " + purchase.getGatewayPurchase()
		+ " por favor verifique e realize os procedimentos necessários para liberar o produto!");
	}


}
