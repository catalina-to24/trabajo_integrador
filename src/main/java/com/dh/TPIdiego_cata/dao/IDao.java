package com.dh.TPIdiego_cata.dao;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);

    T buscarPorId(Integer id);

    void eliminar(Integer id);

    T actualizar(T t);

    List<T> listarTodos();
}
