package com.devsDoAgi.almoxarifado.dto;

import jakarta.validation.constraints.NotBlank;

public record AtribuirEquipamentoRequestDTO(@NotBlank(message = "O email é obrigatorio!") String email) {
}
