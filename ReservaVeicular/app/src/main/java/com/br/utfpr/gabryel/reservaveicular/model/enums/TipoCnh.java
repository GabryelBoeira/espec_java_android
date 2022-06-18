package com.br.utfpr.gabryel.reservaveicular.model.enums;

public enum TipoCnh {
    A("Moto"),
    B("Veículo Leve ate 3.500KG"),
    C("Vans/Veículos Toco"),
    D("Caminhões e Onibus"),
    E("Veículos Pesados");

    private String descricao;
    TipoCnh(final String descricao) {
        this.descricao = descricao;
    }

    String getKey() {
        return this.name();
    }

    String getNome() {
        return this.descricao;
    }
}
