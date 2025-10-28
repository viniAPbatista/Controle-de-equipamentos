package com.devsDoAgi.almoxarifado.model;

import com.devsDoAgi.almoxarifado.enums.Status;
import com.devsDoAgi.almoxarifado.enums.StatusSolicitacaoCompra;
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
public class SolicitacaoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSolicitacaoCompra;

    @Column(nullable = false)
    private String produto;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacaoCompra estado = StatusSolicitacaoCompra.ANALISE;

    @Enumerated(EnumType.STRING)
    private Status statusSolicitacao = Status.ATIVO;
}
