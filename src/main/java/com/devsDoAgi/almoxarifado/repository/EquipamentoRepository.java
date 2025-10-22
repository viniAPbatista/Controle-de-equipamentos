package com.devsDoAgi.almoxarifado.repository;

import com.devsDoAgi.almoxarifado.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EquipamentoRepository extends JpaRepository<Equipamento, UUID> {
}
