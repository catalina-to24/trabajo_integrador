package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.dao.implementation.TurnoDaoList;
import com.dh.TPIdiego_cata.model.Turno;
import com.dh.TPIdiego_cata.service.ITurnoService;

import java.util.List;

public class TurnoService implements ITurnoService {

    private TurnoDaoList turnoDaoList;
    public TurnoService() {
        turnoDaoList = new TurnoDaoList();
    }
    @Override
    public Turno guardar(Turno turno) {
        return turnoDaoList.guardar(turno);
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return turnoDaoList.buscarPorId(id);
    }

    @Override
    public void actualizar(Turno turno) {
        turnoDaoList.actualizar(turno);
    }

    @Override
    public void eliminar(Integer id) {
        turnoDaoList.eliminar(id);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoDaoList.listarTodos();
    }
}
