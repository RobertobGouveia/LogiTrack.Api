package com.logitrack.logitrack_pro.dto.viagem;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViagemUpdateDTO {
    private LocalDateTime dataSaida;
    private LocalDateTime dataChegada;
    private String origem;
    private String destino;
    private BigDecimal kmPercorrida;
}