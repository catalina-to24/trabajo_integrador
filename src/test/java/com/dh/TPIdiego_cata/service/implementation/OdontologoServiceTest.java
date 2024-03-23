package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    @Test
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Nombre odontólgo");
        odontologo.setApellido("Apellido odontólgo");
        odontologo.setMatricula("11223344");
        odontologoService.guardar(odontologo);

        Odontologo odontologoAgregado = odontologoService.buscarPorId(odontologo.getId());

        assertEquals("Nombre odontólgo", odontologoAgregado.getNombre());

    }

    @Test
    public void actualizarOdontologo(){
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Nombre odontólgo");
        odontologo.setApellido("Apellido odontólgo");
        odontologo.setMatricula("11223344");

        odontologoService.guardar(odontologo);
        Odontologo odontologoAgregado = odontologoService.buscarPorId(odontologo.getId());

        odontologoAgregado.setApellido("Nuevo apellido");
        odontologoService.actualizar(odontologoAgregado);

        Odontologo odontologoActualizado = odontologoService.buscarPorId(odontologoAgregado.getId());

        assertEquals("Nuevo apellido",odontologoActualizado.getApellido());
        assertEquals("Nombre odontólgo", odontologoActualizado.getNombre());
    }
    @Test
    public void eliminarOdontologo(){

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Nombre odontólgo");
        odontologo.setApellido("Apellido odontólgo");
        odontologo.setMatricula("11223344");

        odontologoService.guardar(odontologo);
        odontologoService.eliminar(odontologo.getId());
        Odontologo odontologoEliminado = odontologoService.buscarPorId(odontologo.getId());

        assertTrue(odontologoEliminado == null);
    }

    @Test
    public void listarOdontologos(){

        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Nombre odontólgo");
        odontologo.setApellido("Apellido odontólgo");
        odontologo.setMatricula("11223344");

        odontologoService.guardar(odontologo);
        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertTrue(odontologos.size() != 0);
    }
}