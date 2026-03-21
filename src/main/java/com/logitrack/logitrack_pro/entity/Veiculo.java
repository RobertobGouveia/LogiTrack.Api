package com.logitrack.logitrack_pro.entity;

import com.logitrack.logitrack_pro.entity.enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String modelo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;
    private Integer ano;
}