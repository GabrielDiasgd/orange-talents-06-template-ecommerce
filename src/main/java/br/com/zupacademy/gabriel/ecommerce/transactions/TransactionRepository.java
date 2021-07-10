package br.com.zupacademy.gabriel.ecommerce.transactions;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	Optional<Transaction> findByStatusAndSystemPaymentId(TransactionStatus status, Long systemPaymentId);

}
