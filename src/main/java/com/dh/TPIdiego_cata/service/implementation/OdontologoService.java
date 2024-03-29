package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.exceptions.ResourceNotFoundException;
import com.dh.TPIdiego_cata.repository.IOdontologoRepository;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    private IOdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Guardando un odontólogo");
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        logger.info("Buscando odontólogo con ID "+id);
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isPresent()) {
            return odontologoOptional.get();
        }else{
            String message = "No se ha encontrado el odontologo con ID "+id;
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.info("Actualizando odontólogo");
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            odontologoRepository.save(odontologo);
            logger.info("Se guardó odontólogo actualizado");
        }else{
            String message = "No se ha encontrado el odontologo a actualizar con ID "+odontologo.getId();
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public void eliminar(Long id) {
        logger.info("Eliminando odontólogo");
        Optional<Odontologo> odontologoAEliminar = odontologoRepository.findById(id);
        if (odontologoAEliminar.isPresent()) {
            odontologoRepository.deleteById(id);
            logger.info("Se eliminó el odontólgo con ID"+id);
        }else{
            String message = "No se ha encontrado el odontologo a eliminar con ID "+id;
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("Listando todos los odontólogo");
        return odontologoRepository.findAll();
    }

    @Override
    public List<Odontologo> listOrderByNombre() {
        logger.info("Listando todos los odontólogo ordenados por nombre");
        return odontologoRepository.listOrderByNombre();
    }

    @Override
    public List<Odontologo> listOrderByApellido() {
        logger.info("Listando todos los odontólogo ordenados por apellido");
        return odontologoRepository.listOrderByApellido();
    }

    @Override
    public List<Odontologo> listOrderByMatricula() {
        logger.info("Listando todos los odontólogo ordenados por matrícula");
        return odontologoRepository.listOrderByMatricula();
    }

    @Override
    public Odontologo findByMatricula(String matricula) {
        logger.info("Buscando por matrícula al odontólogo");
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findFirstByMatricula(matricula);
        if (odontologoBuscado.isPresent()) {
            return odontologoBuscado.get();
        }else{
            String message = "No se ha encontrado el odontologo con matrícula "+matricula;
            logger.error(message);
            throw new ResourceNotFoundException(message);
        }
    }
}
