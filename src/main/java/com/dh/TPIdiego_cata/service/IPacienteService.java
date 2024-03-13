package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.model.Paciente;

import java.util.List;

public interface IPacienteService {
    public Paciente guardar (Paciente paciente);

    public Paciente buscarPorId(Integer id);

    public void actualizar(Paciente paciente);

    public void eliminar(Integer id);

    public List<Paciente> listarTodos();
}
