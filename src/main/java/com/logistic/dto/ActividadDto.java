package com.logistic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActividadDto {

    private Long idActividad;
    private Date fechaRegistro;
    private String estado;
    private EmpleadoDto fkEmpleado;
    private String descripcion;
    private Integer diasRetraso;
}
