package com.logitrack.logitrack_pro.service;

import com.logitrack.logitrack_pro.dto.manutencao.CreateManutencaoDTO;
import com.logitrack.logitrack_pro.dto.manutencao.ManutencaoUpdateDTO;
import com.logitrack.logitrack_pro.entity.Manutencao;
import com.logitrack.logitrack_pro.entity.Veiculo;
import com.logitrack.logitrack_pro.repository.ManutencaoRepository;
import com.logitrack.logitrack_pro.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;
    private final VeiculoRepository veiculoRepository;

    public ManutencaoService(ManutencaoRepository manutencaoRepository,
                             VeiculoRepository veiculoRepository) {
        this.manutencaoRepository = manutencaoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public Manutencao createManutencao(CreateManutencaoDTO dto) {

        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Manutencao manutencao = new Manutencao();
        manutencao.setVeiculo(veiculo);
        manutencao.setDataInicio(dto.getDataInicio());
        manutencao.setDataFinalizacao(dto.getDataFinalizacao());
        manutencao.setTipoServico(dto.getTipoServico());
        manutencao.setCustoEstimado(dto.getCustoEstimado());
        manutencao.setStatus(dto.getStatus());

        return manutencaoRepository.save(manutencao);
    }

    public List<Manutencao> listar() {
        return manutencaoRepository.findAll();
    }

    public Manutencao updateManutencao(Long id, ManutencaoUpdateDTO dto) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));

        if (dto.getVeiculoId() != null) {
            Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
            manutencao.setVeiculo(veiculo);
        }

        if (dto.getDataInicio() != null) {
            manutencao.setDataInicio(dto.getDataInicio());
        }

        if (dto.getDataFinalizacao() != null) {
            manutencao.setDataFinalizacao(dto.getDataFinalizacao());
        }

        if (dto.getTipoServico() != null) {
            manutencao.setTipoServico(dto.getTipoServico());
        }

        if (dto.getCustoEstimado() != null) {
            manutencao.setCustoEstimado(dto.getCustoEstimado());
        }

        if (dto.getStatus() != null) {
            manutencao.setStatus(dto.getStatus());
        }

        return manutencaoRepository.save(manutencao);
    }

    public void deleteManutencao(Long id) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));

        manutencaoRepository.delete(manutencao);
    }
}