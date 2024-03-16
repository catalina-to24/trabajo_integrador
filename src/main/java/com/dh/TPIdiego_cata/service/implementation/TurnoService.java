package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Turno;
import com.dh.TPIdiego_cata.repository.ITurnoRepository;
import com.dh.TPIdiego_cata.service.ITurnoService;

import java.util.List;
import java.util.Optional;

public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }
    @Override
    public Turno guardar(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public Optional<Turno> buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isPresent()) {
            return turnoOptional;
        } else {
            return null;
        }
    }

    @Override
    public void actualizar(Turno turno) {
        turnoRepository.save(turno);
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }
}
