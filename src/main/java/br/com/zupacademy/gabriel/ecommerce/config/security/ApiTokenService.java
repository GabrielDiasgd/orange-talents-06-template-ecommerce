package br.com.zupacademy.gabriel.ecommerce.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zupacademy.gabriel.ecommerce.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ApiTokenService {

	@Value("${ecommerce.jwt.secret}")
	private String secret;
	@Value("${ecommerce.jwt.expiration}")
	private String expiration;

	public String generateToken(Authentication authentication) {
		User logged = (User) authentication.getPrincipal();
		 Date today = new Date();
		 Date dateExpiration = new Date(today.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("Mercado Livre API - Gabriel")
				.setSubject(logged.getId().toString())
				.setIssuedAt(today)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
