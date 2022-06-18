package com.br.utfpr.gabryel.reservaveicular.model.enums;

public enum FormaDeUso {

    MERCADO("Ir ao Mercado"),
    CONSULTA("Consulta Medica"),
    OFICINA("Ir a Oficina"),
    ATIVIDADE_PESSOAIS("Realizar atividades pessoas"),
    OUTROS("Outros motivos");

    private String descricao;
    FormaDeUso(final String descricao) {
        this.descricao = descricao;
    }

    String getKey() {
        return this.name();
    }

    String getNome() {
        return this.descricao;
    }

}
