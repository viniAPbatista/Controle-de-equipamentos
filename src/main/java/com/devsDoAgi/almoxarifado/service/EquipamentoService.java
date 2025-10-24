package com.devsDoAgi.almoxarifado.service;

import com.devsDoAgi.almoxarifado.dto.AtribuirEquipamentoRequestDTO;
import com.devsDoAgi.almoxarifado.dto.EquipamentoRequestDTO;
import com.devsDoAgi.almoxarifado.enums.Status;
import com.devsDoAgi.almoxarifado.exception.ResourceNotFound;
import com.devsDoAgi.almoxarifado.model.Equipamento;
import com.devsDoAgi.almoxarifado.model.Funcionario;
import com.devsDoAgi.almoxarifado.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    @Autowired
    private final EquipamentoRepository repository;

    @Autowired
    private final FuncionarioService funcionarioService;

    public ResponseEntity<Equipamento> adicionarEquipamento(EquipamentoRequestDTO dto) {

        Equipamento novoEquipamento = new Equipamento();
        novoEquipamento.setNome(dto.nome());
        novoEquipamento.setDescricao(dto.descricao());

        //atribui o equipamento ao funcionario
        if(dto.id_funcionario() != null) {
            Funcionario funcionario = funcionarioService.buscarFuncionarioPeloId(dto.id_funcionario());
            novoEquipamento.setFuncionario(funcionario);
        } else {
            novoEquipamento.setFuncionario(null);
        }

        repository.save(novoEquipamento);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Equipamento> inativarEquipamento(UUID id) {

        Equipamento equipamento = buscarEquipamentoPeloId(id);

        equipamento.setStatusEquipamento(Status.INATIVO);
        repository.save(equipamento);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<Equipamento> listarEstoque() {

        //filtra apenas os equipamentos sem funcionario
        return repository.findAll().stream()
                .filter(equipamento -> equipamento.getFuncionario() == null)
                .collect(Collectors.toList());
    }

    public List<Equipamento> listarEquipamentos() {
        return repository.findAll();
    }

    public ResponseEntity<Equipamento> atribuirEquipamentoParaFuncionario(UUID id, AtribuirEquipamentoRequestDTO dto) {

        Equipamento equipamento = buscarEquipamentoPeloId(id);

        if (equipamento.getFuncionario() != null) {
            throw new RuntimeException("O equipamento já está em uso!");
        }

        equipamento.setFuncionario(funcionarioService.buscarFuncionarioPeloEmail(dto.email()));
        repository.save(equipamento);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Equipamento> desvincularEquipamentoFuncionario(UUID id) {

        Equipamento equipamento = buscarEquipamentoPeloId(id);

        if(equipamento.getFuncionario() == null) {
            throw new RuntimeException("O equipamento já está em estoque!");
        }

        equipamento.setFuncionario(null);
        repository.save(equipamento);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private Equipamento buscarEquipamentoPeloId(UUID id) {

        Equipamento equipamento = repository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Equipamento não encontrado!")
        );

        return equipamento;
    }
}
