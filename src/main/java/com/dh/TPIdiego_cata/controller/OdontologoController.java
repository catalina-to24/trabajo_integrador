package com.dh.TPIdiego_cata.controller;
import com.dh.TPIdiego_cata.model.Odontologo;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import com.dh.TPIdiego_cata.service.implementation.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id) {
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
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizar(odontologo);
            response = ResponseEntity.ok("Se actualizó el odontologo con id " + odontologo.getId());
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el odontólogo a actualizar");
        }
        return response;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        ResponseEntity<String> response;
        Odontologo odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado != null) {
            odontologoService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Eliminado correctamente");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el odontólogo a eliminar");
        }
        return response;
    }

}
