package com.loja.virtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.virtual.model.Usuario;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	/*Configurando o Gerenciador de autenticação*/
	public JWTLoginFilter(String url,AuthenticationManager authenticationManager) {
		
		/*Obriga a autenticar a url*/
		super(new AntPathRequestMatcher(url));
		
		/*Gerenciador de autenticação*/
		setAuthenticationManager(authenticationManager);
	}
	
	
	/*Retorna o usuário ao processar a autenticação*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		/*Obtem o usário*/
		Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class); 
		
		/*Retorna o user com login e senha*/
		return getAuthenticationManager().
				authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		try {
			new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
