package com.logistic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    /**
     * id del empleado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Empleado")
    private Long idEmpleado;

    /**
     * nombre del empleado.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Apellido del empleado.
     */
    @Column(name = "apellido")
    private String apellido;

    /**
     * Telefono del empleado.
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * Correo del empleado.
     */
    @Column(name = "correo")
    private String correo;


}
