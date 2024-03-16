package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.repository.IOdontologoRepository;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isPresent()) {
            return odontologoOptional;
        } else {
            return null;
        }
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminar(Integer id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }
}
