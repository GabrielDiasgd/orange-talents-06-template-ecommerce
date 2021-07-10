package br.com.zupacademy.gabriel.ecommerce.services.email;

public interface EmailSender {

	void send(String from, String to, String subject, String body);

}
