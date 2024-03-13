package com.dh.TPIdiego_cata.dao.implementation;

import com.dh.TPIdiego_cata.dao.IDao;
import com.dh.TPIdiego_cata.model.Turno;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TurnoDaoList implements IDao<Turno> {

    private List<Turno> turnoList = new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        //id = 1 -> nosotros
        turnoList.add(turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoADevolver = null;

        for (Turno t: turnoList) {
            if (t.getId().equals(id)) {
                turnoADevolver = t;
                return turnoADevolver;
            }
        }
        return turnoADevolver;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Turno turno) {

    }

    @Override
    public List<Turno> listarTodos() {
        return null;
    }
}
