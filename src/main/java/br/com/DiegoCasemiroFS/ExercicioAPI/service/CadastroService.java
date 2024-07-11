package br.com.DiegoCasemiroFS.ExercicioAPI.service;

/*
classe service onde estão presentes as regras de negócio da API
 */

import br.com.DiegoCasemiroFS.ExercicioAPI.domain.Cadastro;
import br.com.DiegoCasemiroFS.ExercicioAPI.domain.dto.CadastroDto;
import br.com.DiegoCasemiroFS.ExercicioAPI.exception.CadastroNotFoundException;
import br.com.DiegoCasemiroFS.ExercicioAPI.repository.CadastroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroService {

  private final CadastroRepository cadastroRepository;

  @Autowired
  public CadastroService(CadastroRepository cadastroRepository){
    this.cadastroRepository = cadastroRepository;
  }

  //create
  public Cadastro createCadastro(Cadastro cadastro){
    Cadastro novoCadastro = cadastroRepository.save(cadastro);

    return novoCadastro;
  }

  //read
  public Cadastro findCadastro(Long id) {
    Optional<Cadastro> optionalCadastro = cadastroRepository.findById(id);

    return optionalCadastro.orElseThrow(
        () -> new CadastroNotFoundException("Persona Not Found with id: " + id));
  }

  //update
  @Transactional
  public Cadastro updateCadastro (Long id, CadastroDto cadastroDto){
    Cadastro cadastroAlterado = findCadastro(id);
    cadastroAlterado.setEndereco(cadastroDto.getEndereco());
    cadastroAlterado.setEmail(cadastroDto.getEmail());

    return cadastroAlterado;
  }

  //delete
  @Transactional
  public void deleteCadastro (Long id){
    findCadastro(id);
    cadastroRepository.deleteById(id);
  }

}
