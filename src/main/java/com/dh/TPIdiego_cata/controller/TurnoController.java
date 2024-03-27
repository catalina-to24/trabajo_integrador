package com.dh.TPIdiego_cata.controller;
import com.dh.TPIdiego_cata.dto.TurnoDTO;
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
    public ResponseEntity<TurnoDTO> guardar(@RequestBody TurnoDTO turno) {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @PutMapping
    public ResponseEntity actualizar(@RequestBody TurnoDTO turno) {
        turnoService.actualizar(turno);
        return ResponseEntity.ok("Se actualizó el turno con id " + turno.getId());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
       turnoService.eliminar(id);
       return ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
    }
}
