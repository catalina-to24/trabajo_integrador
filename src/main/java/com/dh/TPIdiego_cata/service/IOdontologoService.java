package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    public Odontologo guardar (Odontologo odontologo);
    public Odontologo buscarPorId(Long id);
    public void actualizar(Odontologo odontologo);
    public void eliminar(Long id);
    public List<Odontologo> listarTodos();
    public List<Odontologo> listOrderByNombre();
    public List<Odontologo> listOrderByApellido();
    public List<Odontologo> listOrderByMatricula();

    public Odontologo findByMatricula(String matricula);
}
