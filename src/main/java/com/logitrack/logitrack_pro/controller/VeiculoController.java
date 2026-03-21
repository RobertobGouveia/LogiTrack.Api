package com.logitrack.logitrack_pro.controller;

import com.logitrack.logitrack_pro.dto.veiculo.VeiculoCreateDTO;
import com.logitrack.logitrack_pro.dto.veiculo.VeiculoUpdateDTO;
import com.logitrack.logitrack_pro.entity.Veiculo;
import com.logitrack.logitrack_pro.service.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService){
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar(){
        List<Veiculo> veiculos = veiculoService.listar();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id){
        Veiculo veiculo = veiculoService.buscarPorId(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<Veiculo> createVeiculo(@RequestBody VeiculoCreateDTO dto){
        Veiculo novoVeiculo = veiculoService.createVeiculo(dto);
        return ResponseEntity.ok(novoVeiculo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable Long id, @RequestBody VeiculoUpdateDTO dto){
        Veiculo updateVeiculo = veiculoService.updateVeiculo(id, dto);
        return ResponseEntity.ok(updateVeiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Veiculo> deleteVeiculo(@PathVariable Long id){
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
