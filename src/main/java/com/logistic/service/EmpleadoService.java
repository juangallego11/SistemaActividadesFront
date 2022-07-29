package com.logistic.service;
import com.logistic.dto.EmpleadoDto;

import java.util.List;

public interface EmpleadoService {

    /**
     * Guarda un cliente
     * @param cliente -> Cliente a guardar
     * @return objeto con el cliente guardado.
     */
    EmpleadoDto guardarCliente (EmpleadoDto cliente);

    /**
     * Retorna un cliente de acuerdo a un id
     * @param idCliente -> Id del cliente
     * @return objeto con el cliente consultar
     */
    EmpleadoDto getCliente (Long idCliente);

    /**
     * Retorna todos los clientes
     * @return lista de clientes almacenados en la base de datos.
     */
    List<EmpleadoDto> getClientes();

    boolean eliminarUsuario(Long id);

}
