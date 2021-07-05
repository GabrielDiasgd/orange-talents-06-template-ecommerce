package br.com.zupacademy.gabriel.ecommerce.products;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gabriel.ecommerce.category.CategoryRepository;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create (@RequestBody @Valid ProductRequest productRequest, @AuthenticationPrincipal User user) {
		Product product = productRequest.toModel(categoryRepository, user);
		productRepository.save(product);
		return ResponseEntity.ok().build();
	}
}
