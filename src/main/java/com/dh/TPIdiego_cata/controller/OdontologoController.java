package com.dh.TPIdiego_cata.controller;
import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import com.dh.TPIdiego_cata.service.implementation.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
        odontologoService.actualizar(odontologo);
        return ResponseEntity.ok("Se actualizó el odontologo con id " + odontologo.getId());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        odontologoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
    }

}
