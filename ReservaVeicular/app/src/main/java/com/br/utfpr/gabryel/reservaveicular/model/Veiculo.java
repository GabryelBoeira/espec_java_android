package com.br.utfpr.gabryel.reservaveicular.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Veiculo {

    private int id;
    private String placa;
    private String cor;
    private String deescricao;
}
