package com.loja.virtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loja.virtual.model.Acesso;
import com.loja.virtual.repository.AcessoRepository;
import com.loja.virtual.service.AcessoService;

@Controller
@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	@Autowired
	private AcessoRepository acesoRepository;
	
	@ResponseBody /*poder dar um retorno da API*/
	 @PostMapping(value = "/salvarAcesso") /*Mapeando a url para receber JSON*/
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { /*Recebe JASON e converte para Objeto, se não colocar fica tudo nulo*/
		
		Acesso acessoSalvo = acessoService.save(acesso);
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}
	
	
	@ResponseBody /*poder dar um retorno da API*/
	 @PostMapping(value = "/deleteAcesso") /*Mapeando a url para receber JSON*/
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { /*Recebe JASON e converte para Objeto, se não colocar fica tudo nulo*/
		
		acesoRepository.deleteById(acesso.getId());
		
		return new ResponseEntity("Acesso Removido",HttpStatus.OK);
	}

}
