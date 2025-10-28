package com.devsDoAgi.almoxarifado.repository;

import com.devsDoAgi.almoxarifado.model.SolicitacaoCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SolicitacaoCompraRepository extends JpaRepository<SolicitacaoCompra, UUID> {
}
