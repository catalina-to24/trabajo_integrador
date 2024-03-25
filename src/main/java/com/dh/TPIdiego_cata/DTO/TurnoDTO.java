package com.dh.TPIdiego_cata.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class TurnoDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private Long pacienteId;
    private Long odontologoId;
}
