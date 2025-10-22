package com.devsDoAgi.almoxarifado.controller;

import com.devsDoAgi.almoxarifado.dto.EquipamentoRequestDTO;
import com.devsDoAgi.almoxarifado.model.Equipamento;
import com.devsDoAgi.almoxarifado.service.EquipamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/equipamento")
@RequiredArgsConstructor
public class EquipamentoController {

    @Autowired
    private final EquipamentoService service;

    @PostMapping("/adicionar")
    public ResponseEntity<Equipamento> adicionarEquipamento(@Valid @RequestBody EquipamentoRequestDTO dto) {
        return service.adicionarEquipamento(dto);
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<Equipamento> inativarEquipamento(@PathVariable UUID id) {
        return service.inativarEquipamento(id);
    }
}
