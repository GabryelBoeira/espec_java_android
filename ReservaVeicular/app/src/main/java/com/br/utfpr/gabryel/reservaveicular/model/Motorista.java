package com.br.utfpr.gabryel.reservaveicular.model;

import com.br.utfpr.gabryel.reservaveicular.model.enums.TipoCnh;

import java.time.LocalDate;

public class Motorista {

    private int id;
    private String nome;
    private LocalDate dtNascimento;
    private TipoCnh cnh;
    private boolean possuiEar;
    private boolean ativo;

    public Motorista() {
    }

    public Motorista(String nome, LocalDate dtNascimento, TipoCnh cnh, boolean possuiEar, boolean ativo) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.cnh = cnh;
        this.possuiEar = possuiEar;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public TipoCnh getCnh() {
        return cnh;
    }

    public void setCnh(TipoCnh cnh) {
        this.cnh = cnh;
    }

    public boolean isPossuiEar() {
        return possuiEar;
    }

    public void setPossuiEar(boolean possuiEar) {
        this.possuiEar = possuiEar;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
