package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.dao.IDao;
import com.dh.TPIdiego_cata.dao.implementation.PacienteDaoH2;
import com.dh.TPIdiego_cata.model.Paciente;
import com.dh.TPIdiego_cata.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService{

    private IDao<Paciente> iDaoPaciente;

    public PacienteService(){
        this.iDaoPaciente = new PacienteDaoH2();
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return iDaoPaciente.guardar(paciente);
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        return iDaoPaciente.buscarPorId(id);
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        return iDaoPaciente.actualizar(paciente);
    }

    @Override
    public void eliminar(Integer id) {
        iDaoPaciente.eliminar(id);
    }

    @Override
    public List<Paciente> listarTodos() {
        return iDaoPaciente.listarTodos();
    }
}

