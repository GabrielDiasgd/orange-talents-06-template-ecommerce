package br.com.zupacademy.gabriel.ecommerce.products.review;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gabriel.ecommerce.products.Product;
import br.com.zupacademy.gabriel.ecommerce.products.ProductRepository;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@RestController
public class AddReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/products/{id}/reviews")
	public ResponseEntity<?> addReview (@RequestBody @Valid ReviewRequest reviewRequest, @PathVariable Long id, @AuthenticationPrincipal User user){
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Review review = reviewRequest.toModel(product.get(), user);
		reviewRepository.save(review);
		return ResponseEntity.ok().build();
	}

}
