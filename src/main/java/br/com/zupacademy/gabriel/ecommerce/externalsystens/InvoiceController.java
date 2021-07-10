package br.com.zupacademy.gabriel.ecommerce.externalsystens;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvoiceController {
	
	@PostMapping("/nota-fiscal")
	public String generateInvoice(@RequestBody @Valid InvoiceRequest request ) {
		
		System.out.println(request) ;
		return "ok";
	}

}
