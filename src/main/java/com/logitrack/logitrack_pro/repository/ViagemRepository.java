package com.logitrack.logitrack_pro.repository;

import com.logitrack.logitrack_pro.entity.Viagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {


    @Query("""
    SELECT COALESCE(SUM(v.kmPercorrida), 0)
    FROM Viagem v
    WHERE (:veiculoId IS NULL OR v.veiculo.id = :veiculoId)
""")
    BigDecimal totalKm(@Param("veiculoId") Long veiculoId);


    @Query("""
    SELECT v.veiculo.tipo, COUNT(v)
    FROM Viagem v
    GROUP BY v.veiculo.tipo
""")
    List<Object[]> volumePorCategoria();


    @Query("""
    SELECT v.veiculo.placa, SUM(v.kmPercorrida)
    FROM Viagem v
    GROUP BY v.veiculo.placa
    ORDER BY SUM(v.kmPercorrida) DESC
""")
    List<Object[]> rankingUtilizacao(Pageable pageable);
}
