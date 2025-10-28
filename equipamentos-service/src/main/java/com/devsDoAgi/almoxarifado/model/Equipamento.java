package com.devsDoAgi.almoxarifado.model;

import com.devsDoAgi.almoxarifado.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idEquipamento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Status statusEquipamento = Status.ATIVO;

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    @JsonIgnore                                             //não mostra a lista de funcionarios dentro de cada equipamento
    private Funcionario funcionario;
}
