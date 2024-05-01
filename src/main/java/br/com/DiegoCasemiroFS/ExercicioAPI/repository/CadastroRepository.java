package br.com.DiegoCasemiroFS.ExercicioAPI.repository;
/*
Essa interface acessa o banco de dados JPA
 */
import br.com.DiegoCasemiroFS.ExercicioAPI.domain.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

}
