package com.devsDoAgi.almoxarifado.controller;

import com.devsDoAgi.almoxarifado.dto.SolicitacaoCompraRequestDTO;
import com.devsDoAgi.almoxarifado.model.SolicitacaoCompra;
import com.devsDoAgi.almoxarifado.service.SolicitacaoCompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/solicitacaoCompra")
@RequiredArgsConstructor
public class SolicitacaoCompraController {

    @Autowired
    private final SolicitacaoCompraService service;

    @PostMapping("/adicionar")
    public ResponseEntity<SolicitacaoCompra> adicionarSolicitacaoCompra(@Valid @RequestBody SolicitacaoCompraRequestDTO dto) {
        return service.adicionarSolicitacaoCompra(dto);
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<SolicitacaoCompra> inativarSolicitacaoCompra(@PathVariable UUID id) {
        return service.inativarSolicitacaoCompra(id);
    }

    @PatchMapping("/aprovar/{id}")
    public ResponseEntity<SolicitacaoCompra> aprovarSolicitacaoCompra(@PathVariable UUID id) {
        return service.aprovarSolicitacaoCompra(id);
    }
}
