package br.com.zupacademy.gabriel.ecommerce.category;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create (@RequestBody @Valid CategoryRequest categoryRequest) {
		Category category = categoryRequest.toModel(categoryRepository);
		categoryRepository.save(category);
		return ResponseEntity.ok().build();
	}

}
