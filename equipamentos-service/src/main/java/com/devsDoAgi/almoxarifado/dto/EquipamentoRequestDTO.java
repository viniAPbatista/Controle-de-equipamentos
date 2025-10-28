package com.devsDoAgi.almoxarifado.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record EquipamentoRequestDTO(@NotBlank(message = "O nome é obrigatório") String nome,
                                    @NotBlank(message = "A desciçaõ é obrigatoria") String descricao,
                                    UUID id_funcionario) {
}
