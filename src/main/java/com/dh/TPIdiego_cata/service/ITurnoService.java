package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    public Turno guardar (Turno turno);

    public Optional<Turno> buscarPorId(Long id);

    public void actualizar(Turno turno);

    public void eliminar(Long id);

    public List<Turno> listarTodos();
}
