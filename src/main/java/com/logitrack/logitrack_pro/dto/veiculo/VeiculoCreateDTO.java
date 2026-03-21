package com.logitrack.logitrack_pro.dto.veiculo;

import com.logitrack.logitrack_pro.entity.enums.TipoVeiculo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoCreateDTO {

    private String placa;
    private String modelo;
    private TipoVeiculo tipo;
    private Integer ano;
}