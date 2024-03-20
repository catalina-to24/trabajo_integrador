package com.dh.TPIdiego_cata;

import com.dh.TPIdiego_cata.entity.Domicilio;
import com.dh.TPIdiego_cata.entity.Paciente;
import com.dh.TPIdiego_cata.repository.IPacienteRepository;
import com.dh.TPIdiego_cata.service.implementation.PacienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TpIdiegoCataApplication {
	public static void main(String[] args) {
		//BD.crearTablas();
		SpringApplication.run(TpIdiegoCataApplication.class, args);
	}

}
