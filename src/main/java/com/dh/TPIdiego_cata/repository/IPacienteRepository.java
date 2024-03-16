package com.dh.TPIdiego_cata.repository;

import com.dh.TPIdiego_cata.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
}
