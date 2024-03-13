package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.model.Odontologo;

import java.util.List;

public interface IOdontologoService {
    public Odontologo guardar (Odontologo odontologo);

    public Odontologo buscarPorId(Integer id);

    public void actualizar(Odontologo odontologo);

    public void eliminar(Integer id);

    public List<Odontologo> listarTodos();
}
