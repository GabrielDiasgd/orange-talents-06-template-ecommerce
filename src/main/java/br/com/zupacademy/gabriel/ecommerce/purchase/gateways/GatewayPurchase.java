package br.com.zupacademy.gabriel.ecommerce.purchase.gateways;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;

public interface GatewayPurchase {

	public String generateGatewayUrl (Purchase purchase, UriComponentsBuilder uriComponentsBuilder);
}
