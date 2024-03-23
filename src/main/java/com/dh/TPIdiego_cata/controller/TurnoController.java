package com.dh.TPIdiego_cata.controller;
import com.dh.TPIdiego_cata.entity.Turno;
import com.dh.TPIdiego_cata.service.ITurnoService;
import com.dh.TPIdiego_cata.service.implementation.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;
    @Autowired
    public TurnoController(TurnoService turnoService){
        this.turnoService = turnoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));

    }
    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @PutMapping
    public ResponseEntity actualizar(@RequestBody Turno turno) {
        ResponseEntity response;
        try{
            turnoService.actualizar(turno);
            response = ResponseEntity.ok("Se actualizó el turno con id " + turno.getId());
        }catch (Exception ex){
             response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el paciente a actualizar");
        }

        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        ResponseEntity response;
        Turno turnobuscado = turnoService.buscarPorId(id);

        if (turnobuscado != null) {
            turnoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el turno a eliminar");
        }
        return response;
    }
}
