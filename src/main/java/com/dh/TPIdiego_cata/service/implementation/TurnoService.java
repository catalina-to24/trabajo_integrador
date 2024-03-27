package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.dto.TurnoDTO;
import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.entity.Turno;
import com.dh.TPIdiego_cata.exceptions.ResourceNotFoundException;
import com.dh.TPIdiego_cata.repository.IOdontologoRepository;
import com.dh.TPIdiego_cata.repository.IPacienteRepository;
import com.dh.TPIdiego_cata.repository.ITurnoRepository;
import com.dh.TPIdiego_cata.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger logger = Logger.getLogger(TurnoService.class);

    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public TurnoDTO guardar(TurnoDTO turnoDTO) {
        logger.info("Guardando un turno");
        Turno turno = new Turno();
        turno.setFechaHora(turnoDTO.getFechaHora());

        if (turnoDTO.getOdontologoId() != null) {
            Odontologo odontologo = odontologoRepository.findById(turnoDTO.getOdontologoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Odontólogo no encontrado con ID "+turnoDTO.getOdontologoId()));
            turno.setOdontologo(odontologo);
        }

        if (turnoDTO.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(turnoDTO.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID "+turnoDTO.getOdontologoId()));
            turno.setPaciente(paciente);
        }

        turnoRepository.save(turno);
        logger.info("Turno guardado");

        turnoDTO.setId(turno.getId());

        return turnoDTO;
    }

    @Override
    public Turno buscarPorId(Long id) {
        logger.info("Buscando un turno con ID "+id);
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isPresent()) {
            return turnoOptional.get();
        }else {
            String message = "No se ha encontrado el turno con ID "+id;
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public void actualizar(TurnoDTO turnoDTO){
        logger.info("Actualizando un turno");
        Turno turno = objectMapper.convertValue(turnoDTO, Turno.class);

        Turno turnoExistente = turnoRepository.findById(turnoDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID "+turnoDTO.getId()));

        turnoExistente.setFechaHora(turnoDTO.getFechaHora());

        if (turnoDTO.getOdontologoId() != null) {
            Odontologo odontologo = odontologoRepository.findById(turnoDTO.getOdontologoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Odontólogo no encontrado con ID "+turnoDTO.getOdontologoId()));
            turnoExistente.setOdontologo(odontologo);
        }

        if (turnoDTO.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(turnoDTO.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID "+turnoDTO.getOdontologoId()));
            turnoExistente.setPaciente(paciente);
        }

        turnoRepository.save(turnoExistente);
        logger.info("Turno actualizado");
    }

    @Override
    public void eliminar(Long id) {
        logger.info("Eliminando un turno con ID "+id);
        Optional<Turno> turnoAEliminar = turnoRepository.findById(id);
        if (turnoAEliminar.isPresent()) {
            turnoRepository.deleteById(id);
            logger.info("Turno eliminado");
        }else{
            String message = "No se ha encontrado el truno a eliminar con ID "+id;
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public List<Turno> listarTodos() {
        logger.info("Listando todos los turnos");
        return turnoRepository.findAll();
    }
}
