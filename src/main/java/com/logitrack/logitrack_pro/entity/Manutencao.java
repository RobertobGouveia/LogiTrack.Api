package com.logitrack.logitrack_pro.entity;

import com.logitrack.logitrack_pro.entity.enums.StatusManutencao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "manutencoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    private LocalDate dataInicio;

    private LocalDate dataFinalizacao;

    private String tipoServico;

    private BigDecimal custoEstimado;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusManutencao status;
}