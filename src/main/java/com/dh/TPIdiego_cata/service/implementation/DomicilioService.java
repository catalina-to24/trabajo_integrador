package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.dao.IDao;
import com.dh.TPIdiego_cata.dao.implementation.DomicilioDaoH2;
import com.dh.TPIdiego_cata.model.Domicilio;
import com.dh.TPIdiego_cata.service.IDomicilioService;

import java.util.List;

public class DomicilioService implements IDomicilioService {
//BORRARRRRRRR
    private IDao<Domicilio> iDao;

    public DomicilioService(){
        this.iDao = new DomicilioDaoH2();
    }

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        return iDao.guardar(domicilio);
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        iDao.actualizar(domicilio);
    }

    @Override
    public void eliminar(Integer id) {
        iDao.eliminar(id);
    }

    @Override
    public List<Domicilio> listarTodos() {
        return iDao.listarTodos();
    }
}
