package com.logitrack.logitrack_pro.service;

import com.logitrack.logitrack_pro.dto.viagem.ViagemCreateDTO;
import com.logitrack.logitrack_pro.dto.viagem.ViagemUpdateDTO;
import com.logitrack.logitrack_pro.entity.Veiculo;
import com.logitrack.logitrack_pro.entity.Viagem;
import com.logitrack.logitrack_pro.repository.VeiculoRepository;
import com.logitrack.logitrack_pro.repository.ViagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final VeiculoRepository veiculoRepository;

    public ViagemService(ViagemRepository viagemRepository, VeiculoRepository veiculoRepository){
        this.viagemRepository = viagemRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public Page<Viagem> listar(Pageable pageable){
        return viagemRepository.findAll(pageable);
    }

    public Viagem buscarPorId(Long id){
        return viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
    }

    public Viagem createViagem(ViagemCreateDTO dto){
        Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Viagem viagem = new Viagem();
        viagem.setVeiculo(veiculo);
        viagem.setDataSaida(dto.getDataSaida());
        viagem.setDataChegada(dto.getDataChegada());
        viagem.setOrigem(dto.getOrigem());
        viagem.setDestino(dto.getDestino());
        viagem.setKmPercorrido(dto.getKmPercorrido());

        return viagemRepository.save(viagem);
    }

    public Viagem updateViagem(Long id, ViagemUpdateDTO dto){
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        if (dto.getDataSaida() != null) {
            viagem.setDataSaida(dto.getDataSaida());
        }

        if (dto.getDataChegada() != null) {
            viagem.setDataChegada(dto.getDataChegada());
        }

        if (dto.getOrigem() != null) {
            viagem.setOrigem(dto.getOrigem());
        }

        if (dto.getDestino() != null) {
            viagem.setDestino(dto.getDestino());
        }

        if (dto.getKmPercorrido() != null) {
            viagem.setKmPercorrido(dto.getKmPercorrido());
        }

        return viagemRepository.save(viagem);
    }


    public void deleteViagem(Long id){
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        viagemRepository.delete((viagem));
    }
}