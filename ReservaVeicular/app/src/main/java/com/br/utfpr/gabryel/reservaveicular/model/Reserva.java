package com.br.utfpr.gabryel.reservaveicular.model;

import com.br.utfpr.gabryel.reservaveicular.model.enums.FormaDeUso;

import java.time.LocalDateTime;

public class Reserva {

    private int id;
    private FormaDeUso tipo;
    private LocalDateTime dtInicio;
    private LocalDateTime dtFim;
    private Motorista motorista;
    private Veiculo veiculo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FormaDeUso getTipo() {
        return tipo;
    }

    public void setTipo(FormaDeUso tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDateTime dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDateTime getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDateTime dtFim) {
        this.dtFim = dtFim;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
