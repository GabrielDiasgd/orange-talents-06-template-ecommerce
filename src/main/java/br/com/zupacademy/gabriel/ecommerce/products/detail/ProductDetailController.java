package br.com.zupacademy.gabriel.ecommerce.products.detail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.products.ProductRepository;

@RestController
public class ProductDetailController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products/{id}/details")
	public ResponseEntity<ProductDetailResponse> showDetails (@PathVariable Long id){
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(new ProductDetailResponse(product.get()));
	}

}
