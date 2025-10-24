package com.devsDoAgi.almoxarifado.service;

import com.devsDoAgi.almoxarifado.dto.SolicitacaoCompraRequestDTO;
import com.devsDoAgi.almoxarifado.enums.Status;
import com.devsDoAgi.almoxarifado.enums.StatusSolicitacaoCompra;
import com.devsDoAgi.almoxarifado.exception.ResourceNotFound;
import com.devsDoAgi.almoxarifado.model.SolicitacaoCompra;
import com.devsDoAgi.almoxarifado.repository.SolicitacaoCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitacaoCompraService {

    @Autowired
    private final SolicitacaoCompraRepository repository;

    public ResponseEntity<SolicitacaoCompra> adicionarSolicitacaoCompra(SolicitacaoCompraRequestDTO dto) {

        SolicitacaoCompra novaSolicitacaoCompra = new SolicitacaoCompra();
        novaSolicitacaoCompra.setProduto(dto.produto());
        novaSolicitacaoCompra.setDescricao(dto.descricao());

        repository.save(novaSolicitacaoCompra);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<SolicitacaoCompra> inativarSolicitacaoCompra(UUID id) {

        SolicitacaoCompra solicitacaoCompra = buscarSolicitacaoCompraPeloId(id);

        solicitacaoCompra.setStatusSolicitacao(Status.INATIVO);
        repository.save(solicitacaoCompra);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<SolicitacaoCompra> aprovarSolicitacaoCompra(UUID id) {

        SolicitacaoCompra solicitacaoCompra = buscarSolicitacaoCompraPeloId(id);

        solicitacaoCompra.setEstado(StatusSolicitacaoCompra.APROVADA);
        repository.save(solicitacaoCompra);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private SolicitacaoCompra buscarSolicitacaoCompraPeloId(UUID id) {

        SolicitacaoCompra solicitacaoCompra = repository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Solicitação de compra não encontrada!")
        );

        return solicitacaoCompra;
    }
}
