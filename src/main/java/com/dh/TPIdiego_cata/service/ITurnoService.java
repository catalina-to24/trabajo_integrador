package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.DTO.TurnoDTO;
import com.dh.TPIdiego_cata.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    public Turno guardar (Turno turno);

    public Turno buscarPorId(Long id);

    public void actualizar(Turno turno);
    //public void actualizar(TurnoDTO turnoDTO);

    public void eliminar(Long id);

    public List<Turno> listarTodos();
}
