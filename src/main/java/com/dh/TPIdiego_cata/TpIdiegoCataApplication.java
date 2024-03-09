package com.dh.TPIdiego_cata;

import com.dh.TPIdiego_cata.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TpIdiegoCataApplication {
	public static void main(String[] args) {
		BD.crearTablas();
		SpringApplication.run(TpIdiegoCataApplication.class, args);
	}

}
