package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.DTO.TurnoDTO;
import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.entity.Turno;
import com.dh.TPIdiego_cata.repository.IOdontologoRepository;
import com.dh.TPIdiego_cata.repository.IPacienteRepository;
import com.dh.TPIdiego_cata.repository.ITurnoRepository;
import com.dh.TPIdiego_cata.service.IPacienteService;
import com.dh.TPIdiego_cata.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    private IOdontologoRepository odontologoRepository;

    private IPacienteRepository pacienteRepository;
    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Autowired
    private ObjectMapper objectMapper;

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

    /*public void actualizar(TurnoDTO turnoDTO){
        // Convertir el DTO a la entidad Turno
        Turno turno = objectMapper.convertValue(turnoDTO, Turno.class);

        // Buscar el turno existente en la base de datos por su ID
        Turno turnoExistente = turnoRepository.findById(turnoDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("Turno no encontrado"));

        // Actualizar los campos permitidos
        turnoExistente.setFechaHora(turno.getFechaHora());

        if (turnoDTO.getOdontologoId() != null) {
            Odontologo odontologo = odontologoRepository.findById(turnoDTO.getOdontologoId())
                    .orElseThrow(() -> new NoSuchElementException("Odontólogo no encontrado"));
            turnoExistente.setOdontologo(odontologo);
        }

        if (turnoDTO.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(turnoDTO.getPacienteId())
                    .orElseThrow(() -> new NoSuchElementException("Paciente no encontrado"));
            turnoExistente.setPaciente(paciente);
        }

        // Guardar el turno actualizado en la base de datos
        turnoRepository.save(turnoExistente);
    }*/

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }
}
