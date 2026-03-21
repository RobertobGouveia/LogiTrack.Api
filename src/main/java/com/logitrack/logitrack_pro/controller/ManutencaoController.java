package com.logitrack.logitrack_pro.controller;

import com.logitrack.logitrack_pro.dto.manutencao.CreateManutencaoDTO;
import com.logitrack.logitrack_pro.dto.manutencao.ManutencaoUpdateDTO;
import com.logitrack.logitrack_pro.entity.Manutencao;
import com.logitrack.logitrack_pro.service.ManutencaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/manutencoes")
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @PostMapping
    public ResponseEntity<Manutencao> create(@RequestBody CreateManutencaoDTO dto) {
        Manutencao manutencao = manutencaoService.createManutencao(dto);
        return ResponseEntity.ok(manutencao);
    }

    @GetMapping
    public ResponseEntity<List<Manutencao>> listar(){
        List<Manutencao> manutencoes = manutencaoService.listar();
        return ResponseEntity.ok(manutencoes);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Manutencao> update(@PathVariable Long id, @RequestBody ManutencaoUpdateDTO dto) {
        Manutencao manutencao = manutencaoService.updateManutencao(id, dto);
        return ResponseEntity.ok(manutencao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Manutencao> delete(@PathVariable Long id) {
        manutencaoService.deleteManutencao(id);
        return ResponseEntity.noContent().build();
    }
}