package com.br.utfpr.gabryel.reservaveicular.model.enums;

import java.util.Arrays;
import java.util.List;

public enum TipoCnh {
    A("A - Moto"),
    B("B - Veículo Leve ate 3.500KG"),
    C("C - Vans/Veículos Toco"),
    D("D - Caminhões e Onibus"),
    E("E - Veículos Pesados");

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

    public static List<String> descricaoList() {
        return Arrays.asList(A.descricao, B.descricao, C.descricao, D.descricao, E.descricao);

    }
}
