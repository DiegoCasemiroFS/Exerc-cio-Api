package br.com.DiegoCasemiroFS.ExercicioAPI.controller;

/*
classe controller responsável por receber as requisições e fazer a ponte com a api
 */

import br.com.DiegoCasemiroFS.ExercicioAPI.domain.Cadastro;
import br.com.DiegoCasemiroFS.ExercicioAPI.domain.dto.CadastroDto;
import br.com.DiegoCasemiroFS.ExercicioAPI.service.CadastroService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/cadastro")
public class CadastroController {

  private final CadastroService cadastroService;

  @Autowired
  public CadastroController(CadastroService cadastroService){
    this.cadastroService = cadastroService;
  }

  @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<Void> createPersona(@Valid @RequestBody Cadastro cadastro){
    Cadastro novoCadastro = null;

    try{
      novoCadastro = cadastroService.createCadastro(cadastro);
    }catch (DataIntegrityViolationException e){
      return ResponseEntity.badRequest().build();
    }

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(novoCadastro.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<Cadastro> findCadastro(@PathVariable Long id){
    Cadastro cadastro = cadastroService.findCadastro(id);

    return ResponseEntity.ok().body(cadastro);
  }

  @RequestMapping (method = RequestMethod.PUT, value = "/{id}")
  public ResponseEntity<Cadastro> updateCadastro(@PathVariable Long id, @RequestBody CadastroDto cadastroDto){
    Cadastro cadastro = cadastroService.updateCadastro(id, cadastroDto);

    return ResponseEntity.ok().body(cadastro);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteCadastro(@PathVariable Long id){
    cadastroService.deleteCadastro(id);

    return ResponseEntity.noContent().build();
  }

}