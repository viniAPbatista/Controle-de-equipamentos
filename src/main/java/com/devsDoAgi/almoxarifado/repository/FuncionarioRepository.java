package com.devsDoAgi.almoxarifado.repository;

import com.devsDoAgi.almoxarifado.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    Optional<Funcionario> findByEmail(String email);

    UserDetails findByCpf(String cpf);
}
