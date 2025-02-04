package com.loja.virtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private AcessoRepository acessoRepository;

    @ResponseBody /* Poder dar um retorno da API */
    @PostMapping(value = "/salvarAcesso") /* Mapeando a URL para receber JSON */
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) { /* Recebe JSON e converte para Objeto */
        Acesso acessoSalvo = acessoService.save(acesso);
        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }

    @ResponseBody /* Poder dar um retorno da API */
    @DeleteMapping(value = "/deleteAcesso") /* Mapeando a URL para receber JSON */
    public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) { /* Recebe JSON e converte para Objeto */
        acessoRepository.deleteById(acesso.getId());
        return new ResponseEntity<>("Acesso Removido", HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/deleteAcessoPorId/{id}")
    public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {
        acessoRepository.deleteById(id);
        return new ResponseEntity<>("Acesso Removido", HttpStatus.OK);
    }
    
    
    //@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" }) /*Só pode deletar se usuário tem o perfil fde gerente ou administrador*/
    @ResponseBody
    @GetMapping(value = "/obterAcesso/{id}")
    public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) {
        Acesso acesso = acessoRepository.findById(id).get();
        return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/buscarPorDesc/{desc}")
    public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) {
        List<Acesso> acesso = acessoRepository.buscarAcessoDesc(desc);
        return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
    }
}
