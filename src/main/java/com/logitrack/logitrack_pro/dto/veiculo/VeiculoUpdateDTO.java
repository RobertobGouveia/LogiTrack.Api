package com.logitrack.logitrack_pro.dto.veiculo;

import com.logitrack.logitrack_pro.entity.enums.TipoVeiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoUpdateDTO {

    private String placa;
    private String modelo;
    private TipoVeiculo tipo;
    private Integer ano;
}