package br.com.zupacademy.gabriel.ecommerce.products;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gabriel.ecommerce.category.CategoryRepository;
import br.com.zupacademy.gabriel.ecommerce.products.image.ImageRequest;
import br.com.zupacademy.gabriel.ecommerce.services.images.Uploader;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private Uploader uploadFake;

	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@RequestBody @Valid ProductRequest productRequest,
			@AuthenticationPrincipal User user) {
		Product product = productRequest.toModel(categoryRepository, user);
		productRepository.save(product);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{id}/images")
	@Transactional
	public ResponseEntity<?> registerImage(@PathVariable Long id, @Valid ImageRequest image, @AuthenticationPrincipal User user) {
		Optional<Product> product = productRepository.findById(id);
		if (!product.get().belongUser(user)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} else {
			Set<String> links = uploadFake.send(image.getImages());
			product.get().setImages(links);
			productRepository.save(product.get());
			return ResponseEntity.ok().build();
		}
		
		
	}
}
