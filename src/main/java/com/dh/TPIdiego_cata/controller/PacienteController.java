package com.dh.TPIdiego_cata.controller;

import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.service.IPacienteService;
import com.dh.TPIdiego_cata.service.implementation.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;
    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));

    }
    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {
        pacienteService.actualizar(paciente);
        return ResponseEntity.ok("Se actualizó el paciente con id " + paciente.getId());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        pacienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
    }
}
