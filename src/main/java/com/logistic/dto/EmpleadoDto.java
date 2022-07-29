package com.logistic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpleadoDto {

    private Long idEmpleado;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

}
