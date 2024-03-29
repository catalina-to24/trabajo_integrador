package com.dh.TPIdiego_cata.service.implementation;

import com.dh.TPIdiego_cata.entity.Odontologo;
import com.dh.TPIdiego_cata.exceptions.ResourceNotFoundException;
import com.dh.TPIdiego_cata.service.IOdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    private Odontologo odontologo;

    @BeforeEach
    public void setUp() {
        // Crea un objeto de tipo Odontologo antes de ejecutar los casos de prueba
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

        //testeamos que se esté tirando una excepción del tipo ResourceNotFoundException
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> odontologoService.buscarPorId(odontologo.getId()));

        //testeamos que el mensaje de la excepción se correponda con el esperado
        assertEquals("No se ha encontrado el odontologo con ID "+odontologo.getId(), exception.getMessage());
    }

    @Test
    public void listarOdontologos(){
        odontologoService.guardar(odontologo);
        List<Odontologo> odontologos = odontologoService.listarTodos();

        assertTrue(odontologos.size() != 0);
    }

    @Test
    public void listarOdontologosPorNombre() {
        odontologoService.guardar(odontologo);

        Odontologo newOdontologo = new Odontologo();
        newOdontologo.setNombre("Andrés");
        newOdontologo.setApellido("Churi");
        newOdontologo.setMatricula("112233");
        odontologoService.guardar(newOdontologo);

        List<Odontologo> odontologos = odontologoService.listOrderByNombre();

        assertEquals(newOdontologo.getNombre(),odontologos.get(0).getNombre());
    }

    @Test
    public void listarOdontologosPorApellido() {
        odontologoService.guardar(odontologo);

        Odontologo newOdontologo = new Odontologo();
        newOdontologo.setNombre("Zion");
        newOdontologo.setApellido("Alexa");
        newOdontologo.setMatricula("112233");
        odontologoService.guardar(newOdontologo);

        List<Odontologo> odontologos = odontologoService.listOrderByApellido();

        assertEquals(newOdontologo.getApellido(),odontologos.get(0).getApellido());
    }

    @Test
    public void listarOdontologosPorMatricula() {
        odontologoService.guardar(odontologo);

        Odontologo newOdontologo = new Odontologo();
        newOdontologo.setNombre("Zion");
        newOdontologo.setApellido("Alexa");
        newOdontologo.setMatricula("000000");
        odontologoService.guardar(newOdontologo);

        List<Odontologo> odontologos = odontologoService.listOrderByMatricula();

        assertEquals(newOdontologo.getMatricula(),odontologos.get(0).getMatricula());
    }

    @Test
    public void buscarOdontologoPorMatricula(){
        odontologoService.guardar(odontologo);

        Odontologo odontologoBuscado = odontologoService.findByMatricula(odontologo.getMatricula());

        assertEquals(odontologo.getMatricula(),odontologoBuscado.getMatricula());


    }
}