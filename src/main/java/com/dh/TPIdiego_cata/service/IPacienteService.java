package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    public Paciente guardar (Paciente paciente);

    public Optional<Paciente> buscarPorId(Long id);

    public void actualizar(Paciente paciente);

    public void eliminar(Long id);

    public List<Paciente> listarTodos();
}
