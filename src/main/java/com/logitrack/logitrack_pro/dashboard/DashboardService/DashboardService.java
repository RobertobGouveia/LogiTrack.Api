package com.logitrack.logitrack_pro.dashboard.DashboardService;

import com.logitrack.logitrack_pro.entity.Manutencao;
import com.logitrack.logitrack_pro.repository.ManutencaoRepository;
import com.logitrack.logitrack_pro.repository.ViagemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class DashboardService {

    private final ViagemRepository viagemRepository;
    private final ManutencaoRepository manutencaoRepository;

    public DashboardService(
            ViagemRepository viagemRepository,
            ManutencaoRepository manutencaoRepository) {
        this.viagemRepository = viagemRepository;
        this.manutencaoRepository = manutencaoRepository;
    }

    public Map<String, Object> obterDashboard() {

        Map<String, Object> dashboard = new HashMap<>();

        BigDecimal totalKm = viagemRepository.totalKm(null);

        List<Object[]> volume = viagemRepository.volumePorCategoria();

        Object ranking = viagemRepository
                .rankingUtilizacao(PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .orElse(null);

        List<Manutencao> proximas = manutencaoRepository
                .findTop5ByDataInicioAfterOrderByDataInicioAsc(LocalDate.now());

        BigDecimal custoMensal = manutencaoRepository.custoMensal();

        dashboard.put("totalKm", totalKm);
        dashboard.put("volumePorCategoria", volume);
        dashboard.put("rankingUtilizacao", ranking);
        dashboard.put("proximasManutencoes", proximas);
        dashboard.put("custoMensal", custoMensal);

        return dashboard;
    }
}