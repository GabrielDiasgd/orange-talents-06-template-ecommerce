package br.com.zupacademy.gabriel.ecommerce.config.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler
	public List<ProblemResponse> handleMethodArgumentNotValidException (MethodArgumentNotValidException exception) {
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		List<ProblemResponse> response = new ArrayList<>();
		
		fieldErrors.forEach(erro -> {
			String message = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			ProblemResponse problem = new ProblemResponse(erro.getField(), message, LocalDateTime.now());
			response.add(problem);
		});
		
		return response;
		
	}

}
