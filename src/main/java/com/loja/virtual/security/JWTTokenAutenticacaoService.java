package com.loja.virtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.loja.virtual.ApplicationContextLoad;
import com.loja.virtual.model.Usuario;
import com.loja.virtual.repository.UsuarioRepository;

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
		
		libercaoCors(response);
		
		/*Usado prara ver no Postman para teste*/
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
	
	/*Retorna o usuário validado com token ou caso não seja valido retorna null*/
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			
			String tokenLimpo = token. replace(TOKEN_PREFIX, "").trim();
			
			/*Faz a validação do token do usuário na requisição e obtem o USER*/
			String user = Jwts.parser().
					setSigningKey(SECRET)
					.parseClaimsJws(tokenLimpo)
					.getBody().getSubject(); /*Para pegar o usuário ex: ADMIN ou jhonny*/
			
			if (user != null) {
				
				Usuario Usuario =  ApplicationContextLoad.
						getApplicationContext().
						getBean(UsuarioRepository.class).findUserByLogin(user);
				
				if (Usuario != null)
				{	return new UsernamePasswordAuthenticationToken(
						Usuario.getLogin(),
						Usuario.getSenha(),
						Usuario.getAuthorities());
					
				}
				
			}
			
			
		}
		
		libercaoCors(response);
		return null;
	}
	
	/*Fazendo liberação contra erro de COrs no navegador*/
	private void libercaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
			
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
			
		}
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
			
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
			
		}
	}

}
