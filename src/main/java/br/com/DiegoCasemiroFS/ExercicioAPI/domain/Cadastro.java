package br.com.DiegoCasemiroFS.ExercicioAPI.domain;
/*
Essa classe serva para declarar os parâmetros do cadastro do cliente
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cadastro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String nome;

  @CPF
  @NotNull
  private String cpf;

  @Email
  @NotNull
  private String email;

  @NotNull
  @JsonFormat(pattern = "dd/MM/yyyy")
  private String nascimento;

  @NotNull
  private String endereco;
}
