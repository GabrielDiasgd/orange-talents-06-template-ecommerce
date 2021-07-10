package br.com.zupacademy.gabriel.ecommerce.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

@Component
public class SendEmailPurchase {

	@Autowired
	private EmailSender email;

	public void forNewPurchase(Purchase purchase) {
		email.send("novacompra@mercadolivre.com.br", purchase.getBuyer().getUsername(), "Nova solicitação de compra",
				"O usuário " + purchase.getBuyer().getUsername() + " está querendo comprar " + purchase.getQuantity()
						+ " unidade(s) do produto " + purchase.getProduct().getName() + " através do " + purchase.getGatewayPurchase()
						+ " por favor verifique e realize os procedimentos necessários para liberar o produto!");
	}

	public void purchaseCompletedSuccessfully(Purchase purchase) {
		email.send("pagamento@mercadolivre.com.br", purchase.getBuyer().getUsername(), "Pagamento aprovado",
				"Olá " + purchase.getBuyer().getUsername() + " seu pagamento de número "
						+ purchase.findSuccessfulTrasactional().getSystemPaymentId() + " foi aprovado com sucesso, o produto "
						+ purchase.getProduct().getName() + " com quantidade " + purchase.getQuantity() + " e valor R$"
						+ purchase.getPrice() + " já está sendo separado e logo será enviado, Agradecemos sua compra "
						+ "e qualquer dúvida pode entrar em contato com o vendedor atráves do email: "
						+ purchase.getProduct().getOwner().getUsername());
	}
	
	public void purchaseUnfinished(Purchase purchase, String uri) {
		email.send("pagemento@mercadoLivre.com.br", purchase.getBuyer().getUsername(), "Pagamento não aprovado!",
				"Olá " + purchase.getBuyer().getUsername() + " seu pagamento não foi aprovado, segue o link para tentar novamente "
						+ "Link: " + uri);
	}

}
