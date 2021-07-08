package br.com.zupacademy.gabriel.ecommerce.purchase;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.products.ProductRepository;
import br.com.zupacademy.gabriel.ecommerce.services.email.EmailSender;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@RestController
public class PurchaseController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private EmailSender emailSender;
	
	@PostMapping("/orders")
	@Transactional
	public String createPurchase (@RequestBody @Valid PurchaseRequest purchaseRequest, 
			@AuthenticationPrincipal User buyer, UriComponentsBuilder uriComponentsBuilder) throws BindException {
		Optional<Product> product = productRepository.findById(purchaseRequest.getProductId());
		
		if (product.get().haveStock(purchaseRequest.getQuantity())) {
			Purchase purchase = new Purchase(purchaseRequest.getQuantity(),product.get(), buyer, purchaseRequest.getGateway());
			purchaseRepository.save(purchase);
			
			emailSender.send(purchase);
			return purchase.generateGateway(uriComponentsBuilder);
		} 

		BindException stockProblem = new BindException(purchaseRequest, "purchaseRequest");
		stockProblem.reject(null, "Não foi possível realizar a compra por conta do estoque");
		
		throw stockProblem;
	}

}
