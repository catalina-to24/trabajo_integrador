package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.dao.IDao;
import com.dh.TPIdiego_cata.dao.implementation.OdontologoDaoH2;
import com.dh.TPIdiego_cata.model.Odontologo;
import com.dh.TPIdiego_cata.model.Paciente;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import com.dh.TPIdiego_cata.service.IPacienteService;

import java.util.List;

public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> iDaoOdontologo;
    public OdontologoService() {
        iDaoOdontologo = new OdontologoDaoH2();
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return iDaoOdontologo.guardar(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return iDaoOdontologo.buscarPorId(id);
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        iDaoOdontologo.actualizar(odontologo);
    }

    @Override
    public void eliminar(Integer id) {
        iDaoOdontologo.eliminar(id);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return iDaoOdontologo.listarTodos();
    }
}
