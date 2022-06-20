package com.br.utfpr.gabryel.reservaveicular.model;

public class Veiculo {

    private int id;
    private String placa;
    private String cor;
    private String deescricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getDeescricao() {
        return deescricao;
    }

    public void setDeescricao(String deescricao) {
        this.deescricao = deescricao;
    }
}
