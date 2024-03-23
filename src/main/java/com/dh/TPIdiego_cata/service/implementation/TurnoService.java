package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Turno;
import com.dh.TPIdiego_cata.repository.ITurnoRepository;
import com.dh.TPIdiego_cata.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }
    @Override
    public Turno guardar(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public Turno buscarPorId(Long id) {
        Turno turno = null;
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isPresent()) {
            turno = turnoOptional.get();
        }
        return turno;
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
