package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.exceptions.ResourceNotFoundException;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    private static Odontologo odontologo;

    @BeforeAll
    public static void setUp() {
        // Crear un objeto de tipo Odontologo antes de ejecutar los casos de prueba
        odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Pérez");
        odontologo.setMatricula("1234");
    }

    @Test
    public void guardarOdontologo(){
        odontologoService.guardar(odontologo);
        Odontologo odontologoAgregado = odontologoService.buscarPorId(odontologo.getId());
        assertEquals("Juan", odontologoAgregado.getNombre());
    }

    @Test
    public void actualizarOdontologo(){
        odontologoService.guardar(odontologo);
        Odontologo odontologoAgregado = odontologoService.buscarPorId(odontologo.getId());

        odontologoAgregado.setApellido("González");
        odontologoService.actualizar(odontologoAgregado);

        Odontologo odontologoActualizado = odontologoService.buscarPorId(odontologoAgregado.getId());

        assertEquals("González",odontologoActualizado.getApellido());
        assertEquals("Juan", odontologoActualizado.getNombre());
    }
    @Test
    public void eliminarOdontologo(){
        odontologoService.guardar(odontologo);
        odontologoService.eliminar(odontologo.getId());
        Odontologo odontologoEliminado = odontologoService.buscarPorId(odontologo.getId());

        assertTrue(odontologoEliminado == null);
        //assertThrows(ResourceNotFoundException.class,)
    }

    @Test
    public void listarOdontologos(){
        odontologoService.guardar(odontologo);
        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertTrue(odontologos.size() != 0);
    }
}