package com.devsDoAgi.almoxarifado.dto;

import com.devsDoAgi.almoxarifado.enums.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioRequestDTO(@NotBlank(message = "O nome é obrigatorio") String nome,
                                    @NotBlank(message = "O email é obrigatorio") String email,
                                    @NotBlank(message = "O cpf é obrigatorio") String cpf,
                                    @NotBlank(message = "A squad é obrigatoria") String squad,
                                    @NotNull(message = "O cargo é obrigatório") Cargo cargo,
                                    @NotBlank(message = "A senha é obrigatoria") String senha) {
}