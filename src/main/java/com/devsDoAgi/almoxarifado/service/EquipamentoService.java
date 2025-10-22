package com.devsDoAgi.almoxarifado.service;

import com.devsDoAgi.almoxarifado.dto.EquipamentoRequestDTO;
import com.devsDoAgi.almoxarifado.enums.Status;
import com.devsDoAgi.almoxarifado.exception.ResourceNotFound;
import com.devsDoAgi.almoxarifado.model.Equipamento;
import com.devsDoAgi.almoxarifado.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    @Autowired
    private final EquipamentoRepository repository;

    public ResponseEntity<Equipamento> adicionarEquipamento(EquipamentoRequestDTO dto) {

        Equipamento novoEquipamento = new Equipamento();
        novoEquipamento.setNome(dto.nome());
        novoEquipamento.setDescricao(dto.descricao());

        repository.save(novoEquipamento);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Equipamento> inativarEquipamento(UUID id) {

        Equipamento equipamento = buscarEquipamentoPeloId(id);

        equipamento.setStatusEquipamento(Status.INATIVO);
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
