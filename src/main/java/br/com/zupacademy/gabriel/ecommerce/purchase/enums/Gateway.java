package br.com.zupacademy.gabriel.ecommerce.purchase.enums;

import br.com.zupacademy.gabriel.ecommerce.purchase.gateways.GatewayPagSeguro;
import br.com.zupacademy.gabriel.ecommerce.purchase.gateways.GatewayPaypal;
import br.com.zupacademy.gabriel.ecommerce.purchase.gateways.GatewayPurchase;

public enum Gateway {

	PAYPAL(new GatewayPaypal()),
	PAGSEGURO(new GatewayPagSeguro());

	private GatewayPurchase gateway;

	Gateway(GatewayPurchase gateway) {
		this.gateway = gateway;
	}
	

	public GatewayPurchase getGateway() {
		return gateway;
	}
}
