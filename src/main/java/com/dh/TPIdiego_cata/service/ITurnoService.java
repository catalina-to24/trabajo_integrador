package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.model.Turno;

import java.util.List;

public interface ITurnoService {
    public Turno guardar (Turno turno);

    public Turno buscarPorId(Integer id);

    public Turno actualizar(Turno turno);

    public void eliminar(Integer id);

    public List<Turno> listarTodos();
}
