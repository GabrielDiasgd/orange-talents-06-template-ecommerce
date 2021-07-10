package br.com.zupacademy.gabriel.ecommerce.transactions;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gabriel.ecommerce.purchase.Purchase;
import br.com.zupacademy.gabriel.ecommerce.purchase.PurchaseRepository;
import br.com.zupacademy.gabriel.ecommerce.transactions.tasks.FinishPurchaseTasks;

@RestController
public class FinishPurchaseContoller {

	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private FinishPurchaseTasks tasks;

	@PostMapping("/retorno-paypal/{id}")
	public ResponseEntity<?> paypalReturn(@RequestBody @Valid PaypalReturnRequest request, @PathVariable Long id, UriComponentsBuilder uri) {
		return process(id, request, uri);
	}

	@PostMapping("/retorno-pagseguro/{id}")
	public ResponseEntity<?> pagseguroReturn(@RequestBody @Valid PagseguroReturnRequest request,
			@PathVariable Long id, UriComponentsBuilder uri) {
		return process(id, request, uri);
	}
	
	@Transactional
	public ResponseEntity<?> process (Long id, CreateTransaction request, UriComponentsBuilder uri) {
		Optional<Purchase> purchase = purchaseRepository.findById(id);
		purchase.get().processTransaction(request);
		purchaseRepository.save(purchase.get());

		if (purchase.get().hasSuccessfulTransaction()) {
			tasks.processCompletedPurchaseTasks(purchase.get());
			return ResponseEntity.ok().build();
		}
		tasks.processesUnfinishedPurchaseTasks(purchase.get(), uri);
		return ResponseEntity.ok().build();
	}
	

}
