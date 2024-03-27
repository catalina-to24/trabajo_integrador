package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Domicilio;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.exceptions.ResourceNotFoundException;
import com.dh.TPIdiego_cata.service.IPacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    private Paciente paciente;

    @BeforeEach
    public void setUp(){
        // Crea un objeto de tipo Paciente y su domicilio antes de ejecutar los casos de prueba
        paciente = new Paciente();
        paciente.setNombre("Rodrigo");
        paciente.setApellido("Moar");
        paciente.setDni("11223344");
        paciente.setFechaIngreso(LocalDate.now());

        Domicilio domicilio = new Domicilio();
        domicilio.setNumero(112233);
        domicilio.setCalle("9 de Julio");
        domicilio.setLocalidad("CABA");
        domicilio.setProvincia("Buenos Aires");
        paciente.setDomicilio(domicilio);
    }

    @Test
    public void guardarPaciente() {
        pacienteService.guardar(paciente);
        Paciente pacienteAgregado = pacienteService.buscarPorId(paciente.getId());

        assertTrue(pacienteAgregado != null);
    }

    @Test
    public void actualizarPaciente(){
        pacienteService.guardar(paciente);
        Paciente pacienteAgregado = pacienteService.buscarPorId(paciente.getId());

        pacienteAgregado.setApellido("Bermúdez");
        pacienteService.actualizar(pacienteAgregado);

        Paciente pacienteActualizado = pacienteService.buscarPorId(pacienteAgregado.getId());

        assertEquals("Bermúdez",pacienteActualizado.getApellido());
        assertEquals("Rodrigo", pacienteActualizado.getNombre());
    }

    @Test
    public void eliminarPaciente(){
        pacienteService.guardar(paciente);
        pacienteService.eliminar(paciente.getId());


        //testeamos que se esté tirando una excepción del tipo ResourceNotFoundException
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> pacienteService.buscarPorId(paciente.getId()));

        //testeamos que el mensaje de la excepción se correponda con el esperado
        assertEquals("No se ha encontrado el paciente con ID "+paciente.getId(), exception.getMessage());
    }

    @Test
    public void listaPacientes(){
        pacienteService.guardar(paciente);
        List<Paciente> pacientes = pacienteService.listarTodos();

        assertTrue(pacientes.size() != 0);
    }
}