package com.devsDoAgi.almoxarifado.service;

import com.devsDoAgi.almoxarifado.dto.FuncionarioRequestDTO;
import com.devsDoAgi.almoxarifado.model.Funcionario;
import com.devsDoAgi.almoxarifado.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    @Autowired
    private final FuncionarioRepository funcionarioRepository;

    public ResponseEntity<Funcionario> adicionarFuncionario (FuncionarioRequestDTO dto) {

        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(dto.nome());
        novoFuncionario.setEmail(dto.email());
        novoFuncionario.setCpf(dto.cpf());
        novoFuncionario.setSquad(dto.squad());
        novoFuncionario.setCargo(dto.cargo());

        funcionarioRepository.save(novoFuncionario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
