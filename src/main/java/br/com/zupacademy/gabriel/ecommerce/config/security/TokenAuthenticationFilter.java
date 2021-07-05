package br.com.zupacademy.gabriel.ecommerce.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.gabriel.ecommerce.user.model.User;
import br.com.zupacademy.gabriel.ecommerce.user.repository.UserRepository;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private ApiTokenService apiTokenService;
	private UserRepository userRepository;

	public TokenAuthenticationFilter(ApiTokenService apiTokenService, UserRepository userRepository) {
		this.apiTokenService = apiTokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recoverToken(request);
		boolean isValid = apiTokenService.isTokenValido(token);
		if (isValid) {
			authenticateUser(token);
		}
		
		filterChain.doFilter(request, response);
	}

	
	
	private void authenticateUser(String token) {
	Long userId = apiTokenService.getUserId(token);
	Optional<User> userLogged = userRepository.findById(userId);
	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userLogged.get(), null, userLogged.get().getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(authentication);		
	}

	private String recoverToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token ==null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
