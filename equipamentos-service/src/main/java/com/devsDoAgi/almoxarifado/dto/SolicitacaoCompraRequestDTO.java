package com.devsDoAgi.almoxarifado.dto;

import jakarta.validation.constraints.NotBlank;

public record SolicitacaoCompraRequestDTO(@NotBlank(message = "O produto é obrigatorio") String produto,
                                          @NotBlank(message = "A descricao é obrigatoria") String descricao) {
}
