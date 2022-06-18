package com.br.utfpr.gabryel.reservaveicular.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Motorista {

    private int id;
    private String nome;
    private LocalDate dtNascimento;
    private String cpf;
}
