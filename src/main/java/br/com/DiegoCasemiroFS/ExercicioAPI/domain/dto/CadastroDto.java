package br.com.DiegoCasemiroFS.ExercicioAPI.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastroDto {
  @Email
  @NotNull
  private String email;

  @NotNull
  private String endereco;

}
