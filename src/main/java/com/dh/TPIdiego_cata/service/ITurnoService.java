package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.dto.TurnoDTO;
import com.dh.TPIdiego_cata.entity.Turno;

import java.util.List;

public interface ITurnoService {
    public TurnoDTO guardar (TurnoDTO turno);

    public Turno buscarPorId(Long id);

    public void actualizar(TurnoDTO turnoDTO);

    public void eliminar(Long id);

    public List<Turno> listarTodos();
}
