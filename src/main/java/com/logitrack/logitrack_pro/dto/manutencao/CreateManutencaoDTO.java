package com.logitrack.logitrack_pro.dto.manutencao;

import com.logitrack.logitrack_pro.entity.enums.StatusManutencao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateManutencaoDTO {

    private Long veiculoId;

    private LocalDate dataInicio;
    private LocalDate dataFinalizacao;

    private String tipoServico;

    private BigDecimal custoEstimado;

    private StatusManutencao status;
}
