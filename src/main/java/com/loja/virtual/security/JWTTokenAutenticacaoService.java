package com.loja.virtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*Criar a autenticação e reornar a Autenticação JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {
	
	/*Token de validade de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	
	/*Pode ser qualquer coisa com o JWT*/
	private static final String SECRET = "swswswswswswsws@!@#***--//";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gra o token e da a resposta para o cliente com o JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		/*Montagem do Token*/
		
		String JWT = Jwts.builder()./*Chama o gerador de token*/
				setSubject(username) /*Adciona o user*/
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) 
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();/*Tempo de expiração*/
		
		/*Exe: Bearer -a*dad9hgdfjdjdkkjjdaldjakalkalfhalfham.fhahfahfiahffaofhoahhu.guuggytrttrrrti*/
		String token = TOKEN_PREFIX + " " + JWT;
		
		/*Mnada a responta para a tela e para o cliente, que pode ser outra API, navegador, aplicativo, javascript, outa chamada Java */
		response.addHeader(HEADER_STRING, token);
		
		/*Usado prara ver no Postman para teste*/
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
	
	

}
