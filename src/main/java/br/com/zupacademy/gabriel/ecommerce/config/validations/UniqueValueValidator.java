package br.com.zupacademy.gabriel.ecommerce.config.validations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;


public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
	
	@PersistenceContext
	private EntityManager manager;
	private String domainAttribute;
	private Class<?> klass;

	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		domainAttribute = constraintAnnotation.fieldName();
		klass = constraintAnnotation.domainClass();
	}
	

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query  = manager.createQuery("Select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <=1, "Foi encontrado mais de um " + klass.getSimpleName() +" cadastrado com esse valor: " + value);
	
		return list.isEmpty();
	}

}
