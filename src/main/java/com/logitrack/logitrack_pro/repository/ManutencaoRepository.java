package com.logitrack.logitrack_pro.repository;

import com.logitrack.logitrack_pro.entity.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    List<Manutencao> findTop5ByDataInicioAfterOrderByDataInicioAsc(LocalDate data);


    @Query("""
    SELECT COALESCE(SUM(m.custoEstimado), 0)
    FROM Manutencao m
    WHERE MONTH(m.dataInicio) = MONTH(CURRENT_DATE)
    AND YEAR(m.dataInicio) = YEAR(CURRENT_DATE)
""")
    BigDecimal custoMensal();
}
