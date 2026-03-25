package com.logitrack.logitrack_pro.repository;

import com.logitrack.logitrack_pro.entity.Viagem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {


    @Query("""
    SELECT COALESCE(SUM(v.kmPercorrida), 0)
    FROM Viagem v
""")
    BigDecimal totalKm();

    @Query("""
    SELECT COALESCE(SUM(v.kmPercorrida), 0)
    FROM Viagem v
    WHERE v.veiculo.id = :veiculoId
    """)

    BigDecimal totalKmPorVeiculo(@Param("veiculoId") Long veiculoId);


    @Query("""
    SELECT v.veiculo.tipo as tipo, COUNT(v) as total
    FROM Viagem v
    GROUP BY tipo
    ORDER BY total DESC
""")
    List<Object[]> volumePorCategoria();


    @Query("""
    SELECT v.veiculo.placa as placa, COALESCE(SUM(v.kmPercorrida), 0) as totalKm
    FROM Viagem v
    GROUP BY v.veiculo.placa
    ORDER BY totalKm DESC, v.veiculo.placa ASC
""")
    List<Object[]> rankingUtilizacao(Pageable pageable);
}
