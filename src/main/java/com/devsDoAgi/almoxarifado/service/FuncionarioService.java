package com.devsDoAgi.almoxarifado.service;

import com.devsDoAgi.almoxarifado.dto.FuncionarioRequestDTO;
import com.devsDoAgi.almoxarifado.dto.LoginRequestDTO;
import com.devsDoAgi.almoxarifado.dto.LoginResponseDTO;
import com.devsDoAgi.almoxarifado.enums.Status;
import com.devsDoAgi.almoxarifado.exception.ResourceNotFound;
import com.devsDoAgi.almoxarifado.model.Funcionario;
import com.devsDoAgi.almoxarifado.repository.FuncionarioRepository;
import com.devsDoAgi.almoxarifado.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ResponseEntity<LoginResponseDTO> login (LoginRequestDTO dto) {

        var nomeSenhaFuncionario = new UsernamePasswordAuthenticationToken(dto.cpf(), dto.senha());
        var auth = this.authenticationManager.authenticate(nomeSenhaFuncionario);

        var token = tokenService.generateToken((Funcionario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    public ResponseEntity<Funcionario> adicionarFuncionario (FuncionarioRequestDTO dto) {

        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(dto.nome());
        novoFuncionario.setEmail(dto.email());
        novoFuncionario.setCpf(dto.cpf());
        novoFuncionario.setSquad(dto.squad());
        novoFuncionario.setCargo(dto.cargo());

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        novoFuncionario.setSenha(senhaCriptografada);

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
