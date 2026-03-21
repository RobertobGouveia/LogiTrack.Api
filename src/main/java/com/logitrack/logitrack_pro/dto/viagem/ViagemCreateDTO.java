package com.logitrack.logitrack_pro.dto.viagem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViagemCreateDTO {
    private Long veiculoId;
    private LocalDateTime dataSaida;
    private LocalDateTime dataChegada;
    private String origem;
    private String destino;
    private BigDecimal kmPercorrido;
}
