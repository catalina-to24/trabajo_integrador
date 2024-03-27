package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Domicilio;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.service.IPacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private IPacienteService pacienteService;

    @Test
    public void guardarPaciente() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Nombre prueba");
        paciente.setApellido("Apellido prueba");
        paciente.setDni("99998888");
        paciente.setFechaIngreso(LocalDate.now());

        Domicilio domicilio = new Domicilio();
        domicilio.setNumero(112233);
        domicilio.setCalle("Calle de prueba");
        domicilio.setLocalidad("Localidad prueba");
        domicilio.setProvincia("Provincia prueba");
        paciente.setDomicilio(domicilio);

        pacienteService.guardar(paciente);
        Paciente pacienteAgregado = pacienteService.buscarPorId(paciente.getId());

        assertTrue(pacienteAgregado != null);
    }

    @Test
    public void actualizarPaciente(){
        Paciente paciente = new Paciente();
        paciente.setNombre("Nombre prueba");
        paciente.setApellido("Apellido prueba");
        paciente.setDni("99998888");
        paciente.setFechaIngreso(LocalDate.now());

        Domicilio domicilio = new Domicilio();
        domicilio.setNumero(112233);
        domicilio.setCalle("Calle de prueba");
        domicilio.setLocalidad("Localidad prueba");
        domicilio.setProvincia("Provincia prueba");
        paciente.setDomicilio(domicilio);

        pacienteService.guardar(paciente);
        Paciente pacienteAgregado = pacienteService.buscarPorId(paciente.getId());

        pacienteAgregado.setApellido("Nuevo apellido");
        pacienteService.actualizar(pacienteAgregado);

        Paciente pacienteActualizado = pacienteService.buscarPorId(pacienteAgregado.getId());

        assertEquals("Nuevo apellido",pacienteActualizado.getApellido());
        assertEquals("Nombre prueba", pacienteActualizado.getNombre());
    }

    @Test
    public void eliminarPaciente(){
        Paciente paciente = new Paciente();
        paciente.setNombre("Nombre prueba");
        paciente.setApellido("Apellido prueba");
        paciente.setDni("99998888");
        paciente.setFechaIngreso(LocalDate.now());

        Domicilio domicilio = new Domicilio();
        domicilio.setNumero(112233);
        domicilio.setCalle("Calle de prueba");
        domicilio.setLocalidad("Localidad prueba");
        domicilio.setProvincia("Provincia prueba");
        paciente.setDomicilio(domicilio);

        pacienteService.guardar(paciente);
        pacienteService.eliminar(paciente.getId());
        Paciente pacienteEliminado = pacienteService.buscarPorId(paciente.getId());

        assertTrue(pacienteEliminado == null);
    }

    @Test
    public void listaPacientes(){

        Paciente paciente = new Paciente();
        paciente.setNombre("Nombre prueba");
        paciente.setApellido("Apellido prueba");
        paciente.setDni("99998888");
        paciente.setFechaIngreso(LocalDate.now());

        Domicilio domicilio = new Domicilio();
        domicilio.setNumero(112233);
        domicilio.setCalle("Calle de prueba");
        domicilio.setLocalidad("Localidad prueba");
        domicilio.setProvincia("Provincia prueba");
        paciente.setDomicilio(domicilio);

        pacienteService.guardar(paciente);

        List<Paciente> pacientes = pacienteService.listarTodos();

        assertTrue(pacientes.size() != 0);
    }
}