package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.dao.IDao;
import com.dh.TPIdiego_cata.dao.implementation.OdontologoDaoH2;
import com.dh.TPIdiego_cata.model.Odontologo;
import com.dh.TPIdiego_cata.model.Paciente;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import com.dh.TPIdiego_cata.service.IPacienteService;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> iDao;
    public OdontologoService() {
        iDao = new OdontologoDaoH2();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iDao.guardar(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return iDao.buscarPorId(id);
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> listarTodos() {
        return iDao.listarTodos();
    }
}
