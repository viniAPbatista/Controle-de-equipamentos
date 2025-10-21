package com.devsDoAgi.almoxarifado.dto;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioRequestDTO(@NotBlank(message = "O nome é obrigatório") String nome,
                                    @NotBlank(message = "O email é obrigatório") String email,
                                    @NotBlank(message = "O cpf é obrigatório") String cpf,
                                    @NotBlank(message = "O squad é obrigatório") String squad,
                                    @NotBlank(message = "O cargo é obrigatório") String cargo) {
}
