package com.devsDoAgi.almoxarifado.enums;

public enum Cargo {
    GERENTE("gerente"),
    ANALISTA("analista");

    private String cargo;

    Cargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}
