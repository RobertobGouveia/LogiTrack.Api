package com.logitrack.logitrack_pro.controller;

import com.logitrack.logitrack_pro.dto.viagem.ViagemCreateDTO;
import com.logitrack.logitrack_pro.dto.viagem.ViagemUpdateDTO;
import com.logitrack.logitrack_pro.entity.Viagem;
import com.logitrack.logitrack_pro.service.ViagemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/viagens")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService){
        this.viagemService = viagemService;
    }

    @GetMapping
    public Page<Viagem> listar(Pageable pageable) {
        return viagemService.listar(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viagem> buscarPorId(@PathVariable Long id){
        Viagem viagem = viagemService.buscarPorId(id);
        return ResponseEntity.ok(viagem);
    }

    @PostMapping
    public ResponseEntity<Viagem> createViagem(@RequestBody ViagemCreateDTO dto){
        Viagem novaViagem = viagemService.createViagem(dto);
        return ResponseEntity.ok(novaViagem);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Viagem> updateViagem(@PathVariable Long id, @RequestBody ViagemUpdateDTO dto){
        Viagem updatedViagem = viagemService.updateViagem(id, dto);
        return ResponseEntity.ok(updatedViagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Viagem> deleteViagem(@PathVariable Long id){
        viagemService.deleteViagem(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/periodo")
//    public Page<Viagem> listarPorPeriodo(
//            @RequestParam LocalDateTime inicio,
//            @RequestParam LocalDateTime fim,
//            Pageable pageable
//    ){
//        return viagemService.listarPorPeriodo(inicio, fim, pageable);
//    }
}