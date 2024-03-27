package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Domicilio;
import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.entity.Turno;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import com.dh.TPIdiego_cata.service.IPacienteService;
import com.dh.TPIdiego_cata.service.ITurnoService;
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

    @Test
    public void guardarTurno(){
        //creamos y guardamos el odontologo
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Nombre odontólgo");
        odontologo.setApellido("Apellido odontólgo");
        odontologo.setMatricula("11223344");
        odontologoService.guardar(odontologo);

        //creamos y guardamos el paciente con un domicilio
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

        //creamos y guardamos el turno
        Turno turno = new Turno();
        turno.setFechaHora(LocalDateTime.now());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        //turnoService.guardar(turno);
        Turno turnoAgregado = turnoService.buscarPorId(turno.getId());

        assertTrue(turnoAgregado != null);

    }

    @Test
    public void actualizarTurno(){
        //creamos y guardamos el odontologo
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Nombre odontólgo");
        odontologo.setApellido("Apellido odontólgo");
        odontologo.setMatricula("11223344");
        odontologoService.guardar(odontologo);

        //creamos y guardamos el paciente con un domicilio
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

        //creamos y guardamos el turno
        Turno turno = new Turno();
        turno.setFechaHora(LocalDateTime.now());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        //turnoService.guardar(turno);

        turno.setFechaHora(LocalDateTime.of(2025,04,25,15,00,00));
        //turnoService.actualizar(turno);

        Turno turnoModificado = turnoService.buscarPorId(turno.getId());

        assertFalse(turno.getFechaHora() == turnoModificado.getFechaHora());

    }
    @Test
    public void eliminarTurno(){
        //creamos y guardamos el odontologo
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Nombre odontólgo");
        odontologo.setApellido("Apellido odontólgo");
        odontologo.setMatricula("11223344");
        odontologoService.guardar(odontologo);

        //creamos y guardamos el paciente con un domicilio
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

        //creamos y guardamos el turno
        Turno turno = new Turno();
        turno.setFechaHora(LocalDateTime.now());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        //turnoService.guardar(turno);

        turnoService.eliminar(turno.getId());

        Turno turnoEliminado = turnoService.buscarPorId(turno.getId());

        assertTrue(turnoEliminado == null);

    }

    @Test
    public void listarTurnos(){
        //creamos y guardamos el odontologo
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Nombre odontólgo");
        odontologo.setApellido("Apellido odontólgo");
        odontologo.setMatricula("11223344");
        odontologoService.guardar(odontologo);

        //creamos y guardamos el paciente con un domicilio
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

        //creamos y guardamos el turno
        Turno turno = new Turno();
        turno.setFechaHora(LocalDateTime.now());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        //turnoService.guardar(turno);

        List<Turno> turnosList = turnoService.listarTodos();

        assertTrue(turnosList.size() != 0);
    }

}