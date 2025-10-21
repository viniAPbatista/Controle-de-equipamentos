package com.devsDoAgi.almoxarifado.controller;

import com.devsDoAgi.almoxarifado.dto.FuncionarioRequestDTO;
import com.devsDoAgi.almoxarifado.model.Funcionario;
import com.devsDoAgi.almoxarifado.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    @Autowired
    private final FuncionarioService service;

    @PostMapping
    public ResponseEntity<Funcionario> adicionarFuncionario(@Valid @RequestBody FuncionarioRequestDTO dto) {
        return service.adicionarFuncionario(dto);
    }
}
