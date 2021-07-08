package br.com.zupacademy.gabriel.ecommerce.purchase.gateways;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

public class GatewayPaypal implements GatewayPurchase {
	
	@Override
	public String generateGatewayUrl(Purchase purchase, UriComponentsBuilder uriComponentsBuilder) {
		String returnUrl = uriComponentsBuilder.path("retorno-paypal/{id}").buildAndExpand(purchase.getId()).toString();
		return String.format("paypal.com?buyerId=%d&redirectUrl=%s", purchase.getId(), returnUrl);
	
	}
}