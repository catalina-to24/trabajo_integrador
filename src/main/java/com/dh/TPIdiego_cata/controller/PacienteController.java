package com.dh.TPIdiego_cata.controller;

import com.dh.TPIdiego_cata.model.Paciente;
import com.dh.TPIdiego_cata.service.IPacienteService;
import com.dh.TPIdiego_cata.service.implementation.PacienteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;
    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @PostMapping("/crear")
    public Paciente crear(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    @GetMapping("/buscar/{id}")
    public Paciente buscarPorId(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
    }
}
