package com.dh.TPIdiego_cata.service;

import com.dh.TPIdiego_cata.model.Domicilio;

import java.util.List;

public interface IDomicilioService {

    public Domicilio guardar (Domicilio domicilio);

    public Domicilio buscarPorId(Integer id);

    public void actualizar(Domicilio domicilio);

    public void eliminar(Integer id);

    public List<Domicilio> listarTodos();
}
