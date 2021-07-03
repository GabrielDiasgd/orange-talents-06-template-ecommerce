package br.com.zupacademy.gabriel.ecommerce.config.validations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

	@PersistenceContext
	private EntityManager manager;
	private String domainAttribute;
	private Class<?> klass;

	@Override
	public void initialize(ExistsId constraintAnnotation) {
		domainAttribute = constraintAnnotation.fieldName();
		klass = constraintAnnotation.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		Query query = manager.createQuery("Select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.isTrue(list.size() <=1, "Existe mais de uma " + klass.getSimpleName() + " com ID de valor: " + value);

		return !list.isEmpty();
	}

}
