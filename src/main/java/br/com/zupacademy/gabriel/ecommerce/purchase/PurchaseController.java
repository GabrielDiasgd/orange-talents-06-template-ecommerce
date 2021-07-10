package br.com.zupacademy.gabriel.ecommerce.purchase;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.products.ProductRepository;
import br.com.zupacademy.gabriel.ecommerce.services.email.SendEmailPurchase;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@RestController
public class PurchaseController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private SendEmailPurchase sendEmail;
	
	@PostMapping("/purchase")
	@Transactional
	public ResponseEntity<?> createPurchase (@RequestBody @Valid PurchaseRequest purchaseRequest, 
			@AuthenticationPrincipal User buyer, UriComponentsBuilder uriComponentsBuilder) throws BindException {
		Optional<Product> product = productRepository.findById(purchaseRequest.getProductId());
		
		if (product.get().haveStock(purchaseRequest.getQuantity())) {
			Purchase purchase = new Purchase(purchaseRequest.getQuantity(),product.get(), buyer, purchaseRequest.getGateway());
			purchaseRepository.save(purchase);
			
			
			sendEmail.forNewPurchase(purchase);
			return ResponseEntity.status(HttpStatus.FOUND).body(purchase.generateGateway(uriComponentsBuilder));
		} 
		
		return ResponseEntity.badRequest().body("Não foi possível finalizar a compra "
				+ "pois o produto não tem estoque suficiente para quantidade requisitada de " + purchaseRequest.getQuantity() + " unidades" );
	}

}
