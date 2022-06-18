package com.br.utfpr.gabryel.reservaveicular.model;

import com.br.utfpr.gabryel.reservaveicular.model.enums.FormaDeUso;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reserva {

    private int id;
    private FormaDeUso tipo;
    private LocalDateTime dtInicio;
    private LocalDateTime dtFim;
    private Motorista motorista;
    private Veiculo veiculo;
}
