package com.br.utfpr.gabryel.reservaveicular.model;

import android.os.Bundle;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.br.utfpr.gabryel.reservaveicular.model.enums.TipoCnh;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(tableName = "motoristas")
public class Motorista {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private LocalDate dtNascimento;

    @NotNull
    private TipoCnh cnh;

    private boolean possuiEar;
    private boolean ativo = true;

    public Motorista() {}

    public Motorista(@NotNull Long id, @NotNull String nome, @NotNull LocalDate dtNascimento, @NotNull TipoCnh cnh, boolean possuiEar, boolean ativo) {
        if (id != -1) this.id = id;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.cnh = cnh;
        this.possuiEar = possuiEar;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Motorista bundleParseMotorista(Bundle bundle) {
        this.id = bundle.getLong("id_motorista");
        this.nome = bundle.getString("nome_motorista");
        this.dtNascimento = LocalDate.parse(bundle.getString("dt_nascimento_motorista"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.cnh = TipoCnh.valueOf(bundle.getString("cnh_motorista"));
        this.possuiEar = bundle.getBoolean("ear_motorista");
        this.ativo = bundle.getBoolean("ativo_motorista");
        return this;
    }

    public static Bundle motoristaParseBundle(Motorista motorista) {
        Bundle bundle = new Bundle();
        bundle.putLong("id_motorista", motorista.getId());
        bundle.putString("nome_motorista", motorista.getNome());
        bundle.putString("dt_nascimento_motorista", motorista.getDtNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        bundle.putString("cnh_motorista", motorista.getCnh().getKey());
        bundle.putBoolean("ear_motorista", motorista.isPossuiEar());
        bundle.putBoolean("ativo_motorista", motorista.isAtivo());
        return bundle;
    }
}
