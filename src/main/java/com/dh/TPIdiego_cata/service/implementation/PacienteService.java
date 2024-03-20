package com.dh.TPIdiego_cata.service.implementation;
import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.repository.IPacienteRepository;
import com.dh.TPIdiego_cata.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService{

    private IPacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository)
    {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Paciente paciente = null;
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()) {
            paciente = pacienteOptional.get();
        }
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}

