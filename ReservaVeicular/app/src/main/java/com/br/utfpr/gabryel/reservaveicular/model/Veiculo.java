package com.br.utfpr.gabryel.reservaveicular.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "veiculos")
public class Veiculo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String placa;
    private String cor;
    private String descricao;

    public Veiculo() {
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
