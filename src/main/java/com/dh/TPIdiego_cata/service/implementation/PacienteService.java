package com.dh.TPIdiego_cata.service.implementation;
import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.exceptions.ResourceNotFoundException;
import com.dh.TPIdiego_cata.repository.IPacienteRepository;
import com.dh.TPIdiego_cata.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService{
    private static final Logger logger = Logger.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository)
    {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("Guardando un paciente");
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscarPorId(Long id) {
        logger.info("Buscando un paciente con ID "+id);
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()) {
            return pacienteOptional.get();
        }else{
            String message = "No se ha encontrado el paciente con ID "+id;
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }

    }

    @Override
    public void actualizar(Paciente paciente) {
        logger.info("Actualizando un paciente");
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            pacienteRepository.save(paciente);
            logger.info("Paciente actualizado");
        }else{
            String message = "No se ha encontrado el paciente a actualizar con ID "+paciente.getId();
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public void eliminar(Long id) {
        logger.info("Eliminando un paciente con ID"+id);
        Optional<Paciente> pacienteAEliminar = pacienteRepository.findById(id);
        if (pacienteAEliminar.isPresent()) {
            pacienteRepository.deleteById(id);
            logger.info("Paciente eliminado");
        }else{
            String message = "No se ha encontrado el paciente a eliminar con ID "+id;
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }

    }

    @Override
    public List<Paciente> listarTodos() {
        logger.info("Listando todos los pacientes");
        return pacienteRepository.findAll();
    }
}

