package com.devsDoAgi.almoxarifado.controller;

import com.devsDoAgi.almoxarifado.dto.FuncionarioRequestDTO;
import com.devsDoAgi.almoxarifado.dto.LoginRequestDTO;
import com.devsDoAgi.almoxarifado.dto.LoginResponseDTO;
import com.devsDoAgi.almoxarifado.model.Funcionario;
import com.devsDoAgi.almoxarifado.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@Valid @RequestBody LoginRequestDTO dto) {
        return service.login(dto);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Funcionario> adicionarFuncionario(@Valid @RequestBody FuncionarioRequestDTO dto) {
        return service.adicionarFuncionario(dto);
    }

    @PatchMapping("/inativar/{id}")
    public ResponseEntity<Funcionario> inativarFuncionario(@PathVariable UUID id) {
        return service.inativarFuncionario(id);
    }

    @GetMapping("/listar")
    public List<Funcionario> listarFuncionarios() {
        return service.listarFuncionarios();
    }
}
