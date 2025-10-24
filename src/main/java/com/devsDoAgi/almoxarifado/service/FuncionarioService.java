package com.devsDoAgi.almoxarifado.service;

import com.devsDoAgi.almoxarifado.dto.FuncionarioRequestDTO;
import com.devsDoAgi.almoxarifado.enums.Status;
import com.devsDoAgi.almoxarifado.exception.ResourceNotFound;
import com.devsDoAgi.almoxarifado.model.Funcionario;
import com.devsDoAgi.almoxarifado.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public ResponseEntity<Funcionario> inativarFuncionario(UUID id) {

        Funcionario funcionario = buscarFuncionarioPeloId(id);

        funcionario.setStatusFuncionario(Status.INATIVO);
        funcionarioRepository.save(funcionario);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<Funcionario> listarFuncionarios() {

        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPeloId(UUID id) {

        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Funcionario não encontrado!")
        );

        return funcionario;
    }

    public Funcionario buscarFuncionarioPeloEmail(String email) {

        Funcionario funcionario = funcionarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFound("Usuario não encontrado!")
        );

        return funcionario;
    }
}
