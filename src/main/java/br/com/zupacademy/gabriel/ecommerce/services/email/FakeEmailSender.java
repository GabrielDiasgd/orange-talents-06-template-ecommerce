package br.com.zupacademy.gabriel.ecommerce.services.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeEmailSender implements EmailSender {

	@Override
	public void send(String from, String to, String subject, String body) {
		System.out.println("De: " + from + " --------- " + "Para: "  + to);
		System.out.println(subject);
		System.out.println(body);
	}

}
