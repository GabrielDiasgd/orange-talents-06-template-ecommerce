package br.com.zupacademy.gabriel.ecommerce.purchase.gateways;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

public class GatewayPagSeguro implements GatewayPurchase{

	@Override
	public String generateGatewayUrl(Purchase purchase, UriComponentsBuilder uriComponentsBuilder) {
		String returnUrl = uriComponentsBuilder.path("retorno-pagseguro/{id}").buildAndExpand(purchase.getId()).toString();
		return String.format("pagseguro.com?returnId=%d&redirectUrl=%s", purchase.getId(), returnUrl);
	}

}
