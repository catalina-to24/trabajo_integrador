package com.dh.TPIdiego_cata.controller;

import com.dh.TPIdiego_cata.model.Paciente;
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
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id) {
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
        ResponseEntity<String> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizar(paciente);
            response = ResponseEntity.ok("Se actualizó el paciente con id " + pacienteBuscado.getId());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paciente a actualizar");
        }
        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        ResponseEntity<String> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(id);
        if (pacienteBuscado != null) {
            pacienteService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paciente a eliminar");
        }
        return response;
    }
}
