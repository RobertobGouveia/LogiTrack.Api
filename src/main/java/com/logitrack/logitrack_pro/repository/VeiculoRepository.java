package com.logitrack.logitrack_pro.repository;

import com.logitrack.logitrack_pro.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    boolean existsByPlaca(String placa);
}
