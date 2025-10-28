package com.devsDoAgi.almoxarifado.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank(message = "O cpf é obrigatorio") String cpf,
                              @NotBlank(message = "A senha é obrigatoria") String senha) {
}
