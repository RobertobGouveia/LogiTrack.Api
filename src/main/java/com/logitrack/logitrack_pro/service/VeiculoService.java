package com.logitrack.logitrack_pro.service;

import com.logitrack.logitrack_pro.dto.veiculo.VeiculoCreateDTO;
import com.logitrack.logitrack_pro.dto.veiculo.VeiculoUpdateDTO;
import com.logitrack.logitrack_pro.entity.Veiculo;
import com.logitrack.logitrack_pro.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listar(){
        return veiculoRepository.findAll();
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }

    public Veiculo createVeiculo(VeiculoCreateDTO dto){
        boolean placaExiste = veiculoRepository.existsByPlaca(dto.getPlaca());

        if(placaExiste){
            throw new RuntimeException("Já existe um veículo com essa placa");
        }

        Veiculo veiculo = new Veiculo();

        veiculo.setPlaca(dto.getPlaca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setTipo(dto.getTipo());
        veiculo.setAno(dto.getAno());

        return veiculoRepository.save(veiculo);
    }

    public Veiculo updateVeiculo(Long id, VeiculoUpdateDTO dto) {

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        if (dto.getPlaca() != null &&
                !dto.getPlaca().equals(veiculo.getPlaca()) &&
                veiculoRepository.existsByPlaca(dto.getPlaca())) {

            throw new RuntimeException("Já existe um veículo com essa placa");
        }

        if (dto.getPlaca() != null) {
            veiculo.setPlaca(dto.getPlaca());
        }

        if (dto.getModelo() != null) {
            veiculo.setModelo(dto.getModelo());
        }

        if (dto.getTipo() != null) {
            veiculo.setTipo(dto.getTipo());
        }

        if (dto.getAno() != null) {
            veiculo.setAno(dto.getAno());
        }

        return veiculoRepository.save(veiculo);
    }

    public void deleteVeiculo(Long id){
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        veiculoRepository.delete(veiculo);
    }
}
