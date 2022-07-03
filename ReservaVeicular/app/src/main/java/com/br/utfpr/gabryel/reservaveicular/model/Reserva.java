package com.br.utfpr.gabryel.reservaveicular.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.br.utfpr.gabryel.reservaveicular.model.enums.FormaDeUso;

import java.time.LocalDate;

@Entity(tableName = "reservas",
        foreignKeys = {
                @ForeignKey(entity = Motorista.class,
                        parentColumns = "id",
                        childColumns = "idMotorista"),
                @ForeignKey(entity = Veiculo.class,
                        parentColumns = "id",
                        childColumns = "idVeiculo")
        })
public class Reserva {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private FormaDeUso tipo;
    private LocalDate inicio;
    private LocalDate fim;

    @ColumnInfo(index = true)
    private Long idMotorista;

    @ColumnInfo(index = true)
    private Long idVeiculo;

    public Reserva() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Long idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public FormaDeUso getTipo() {
        return tipo;
    }

    public void setTipo(FormaDeUso tipo) {
        this.tipo = tipo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }
}
