package br.com.zupacademy.gabriel.ecommerce.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.zupacademy.gabriel.ecommerce.user.repository.UserRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ApiTokenService apiTokenService;
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/categories").permitAll()
			.antMatchers(HttpMethod.POST, "/api/auth").permitAll()
			.antMatchers(HttpMethod.POST, "/users").permitAll()
			.antMatchers(HttpMethod.POST, "/nota-fiscal").permitAll()
			.antMatchers(HttpMethod.POST, "/ranking-vendedores").permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new TokenAuthenticationFilter(apiTokenService, userRepository),UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

}
