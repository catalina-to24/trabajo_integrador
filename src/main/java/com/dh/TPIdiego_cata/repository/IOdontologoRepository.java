package com.dh.TPIdiego_cata.repository;

import com.dh.TPIdiego_cata.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    @Query("FROM Odontologo o ORDER BY o.matricula ASC")
    List<Odontologo> listOrderByMatricula();

    @Query("FROM Odontologo o ORDER BY o.nombre ASC")
    List<Odontologo> listOrderByNombre();

    @Query("FROM Odontologo o ORDER BY o.apellido ASC")
    List<Odontologo> listOrderByApellido();

    Optional<Odontologo> findByMatricula(String matricula);
}
