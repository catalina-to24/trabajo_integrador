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
}
