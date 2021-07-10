package br.com.zupacademy.gabriel.ecommerce.products.question;

import java.util.Optional;

import javax.transaction.Transactional;
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
import br.com.zupacademy.gabriel.ecommerce.services.email.SendEmailQuestion;
import br.com.zupacademy.gabriel.ecommerce.user.model.User;

@RestController
public class AddQuestionController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private SendEmailQuestion sendEmail;

	@Transactional
	@PostMapping("/products/{id}/questions")
	public ResponseEntity<?> addQuestion (@RequestBody @Valid QuestionRequest questionRequest,
			@PathVariable Long id, @AuthenticationPrincipal User user){
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Question question = questionRequest.toModel(user, product.get());
		questionRepository.save(question);
		sendEmail.forNewQuestion(question);
		
		return ResponseEntity.ok().build();
	}

}
