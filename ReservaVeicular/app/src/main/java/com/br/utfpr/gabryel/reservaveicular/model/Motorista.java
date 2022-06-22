package com.br.utfpr.gabryel.reservaveicular.model;

import android.os.Bundle;

import com.br.utfpr.gabryel.reservaveicular.model.enums.TipoCnh;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Motorista {

    private int id;
    private String nome;
    private LocalDate dtNascimento;
    private TipoCnh cnh;
    private boolean possuiEar;
    private boolean ativo;

    public Motorista() {
    }

    public Motorista(int id, String nome, LocalDate dtNascimento, TipoCnh cnh, boolean possuiEar, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.cnh = cnh;
        this.possuiEar = possuiEar;
        this.ativo = ativo;
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

    public ArrayList<Motorista> criarBaseDados() {
        var motoristaList = new ArrayList<Motorista>();
        motoristaList.add(new Motorista(1, "Julian", LocalDate.parse("12/27/6816", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.A, true, true));
        motoristaList.add(new Motorista(2, "Bart", LocalDate.parse("07/05/7081", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.B, true, true));
        motoristaList.add(new Motorista(3, "Jenna", LocalDate.parse("02/10/9862", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.C, false, true));
        motoristaList.add(new Motorista(4, "Sylvia", LocalDate.parse("05/18/6156", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.D, true, false));
        motoristaList.add(new Motorista(5, "William", LocalDate.parse("10/19/3672", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.E, true, false));
        motoristaList.add(new Motorista(6, "Anais", LocalDate.parse("08/06/2058", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.A, true, false));
        motoristaList.add(new Motorista(7, "Rylee", LocalDate.parse("05/13/4198", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.B, true, true));
        motoristaList.add(new Motorista(8, "Bob", LocalDate.parse("03/10/5904", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.C, true, true));
        motoristaList.add(new Motorista(9, "Barney", LocalDate.parse("05/23/7561", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.D, false, true));
        motoristaList.add(new Motorista(10, "Cecilia", LocalDate.parse("02/09/0912", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.E, false, true));
        motoristaList.add(new Motorista(11, "Harvey", LocalDate.parse("07/09/6393", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.A, false, false));
        motoristaList.add(new Motorista(12, "Ember", LocalDate.parse("03/27/4605", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.B, false, false));
        motoristaList.add(new Motorista(13, "Rocco", LocalDate.parse("10/07/1781", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.C, false, false));
        motoristaList.add(new Motorista(14, "Tom", LocalDate.parse("03/16/7955", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.D, false, false));
        motoristaList.add(new Motorista(15, "Wade", LocalDate.parse("03/27/3488", DateTimeFormatter.ofPattern("MM/dd/yyyy")), TipoCnh.E, true, true));
        return motoristaList;
    }

    public Motorista bundleParseMotorista(Bundle bundle, int id) {
        this.id = id;
        this.nome = bundle.getString("nome_motorista");
        this.dtNascimento = LocalDate.parse(bundle.getString("dt_nascimento_motorista"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.cnh = TipoCnh.valueOf(bundle.getString("cnh_motorista"));
        this.possuiEar = bundle.getBoolean("ear_motorista");
        this.ativo = bundle.getBoolean("ativo_motorista");
        return this;
    }
}
