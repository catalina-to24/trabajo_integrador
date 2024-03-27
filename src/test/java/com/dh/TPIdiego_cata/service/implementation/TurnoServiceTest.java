package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.dto.TurnoDTO;
import com.dh.TPIdiego_cata.entity.Domicilio;
import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.entity.Turno;
import com.dh.TPIdiego_cata.exceptions.ResourceNotFoundException;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import com.dh.TPIdiego_cata.service.IPacienteService;
import com.dh.TPIdiego_cata.service.ITurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;
    @Autowired
    IPacienteService pacienteService;
    @Autowired
    ITurnoService turnoService;

    private Odontologo odontologo;
    private Paciente paciente;
    private Turno turno;

    @BeforeEach
    public void setUp(){
        //creamos y guardamos el odontologo
        odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Pérez");
        odontologo.setMatricula("11223344");
        odontologoService.guardar(odontologo);

        //creamos y guardamos el paciente con un domicilio
        paciente = new Paciente();
        paciente.setNombre("Carlos");
        paciente.setApellido("Romano");
        paciente.setDni("998877");
        paciente.setFechaIngreso(LocalDate.now());
        Domicilio domicilio = new Domicilio();
        domicilio.setNumero(112233);
        domicilio.setCalle("Rivadavia");
        domicilio.setLocalidad("CABA");
        domicilio.setProvincia("Buenos Aires");
        paciente.setDomicilio(domicilio);
        pacienteService.guardar(paciente);
    }

    @Test
    public void guardarTurno(){
        //creamos y guardamos el turno
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFechaHora(LocalDateTime.now());
        turnoDTO.setOdontologoId(odontologo.getId());
        turnoDTO.setPacienteId(paciente.getId());

        turnoService.guardar(turnoDTO);
        Turno turnoAgregado = turnoService.buscarPorId(turnoDTO.getId());

        assertTrue(turnoAgregado != null);

    }

    @Test
    public void actualizarTurno(){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFechaHora(LocalDateTime.now());
        turnoDTO.setOdontologoId(odontologo.getId());
        turnoDTO.setPacienteId(paciente.getId());

        turnoService.guardar(turnoDTO);

        turnoDTO.setFechaHora(LocalDateTime.of(2025,04,25,15,00,00));
        turnoService.actualizar(turnoDTO);

        Turno turnoModificado = turnoService.buscarPorId(turnoDTO.getId());

        assertEquals(turnoDTO.getFechaHora(), turnoModificado.getFechaHora());

    }
    @Test
    public void eliminarTurno(){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFechaHora(LocalDateTime.now());
        turnoDTO.setOdontologoId(odontologo.getId());
        turnoDTO.setPacienteId(paciente.getId());

        turnoService.guardar(turnoDTO);

        turnoService.eliminar(turnoDTO.getId());

        //testeamos que se esté tirando una excepción del tipo ResourceNotFoundException
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarPorId(turnoDTO.getId()));

        //testeamos que el mensaje de la excepción se correponda con el esperado
        assertEquals("No se ha encontrado el turno con ID "+turnoDTO.getId(), exception.getMessage());
    }

    @Test
    public void listarTurnos(){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFechaHora(LocalDateTime.now());
        turnoDTO.setOdontologoId(odontologo.getId());
        turnoDTO.setPacienteId(paciente.getId());

        turnoService.guardar(turnoDTO);

        List<Turno> turnosList = turnoService.listarTodos();

        assertTrue(turnosList.size() != 0);
    }

}