package com.logistic.service;

import com.logistic.dto.ActividadDto;

import java.util.List;

public interface ActividadService {


    ActividadDto guardarEnvio(ActividadDto envio);

    ActividadDto obtenerEnvio(Long idEnvio);

    List<ActividadDto> obtenerTodos();

    Boolean eliminarActividad(Long id);

    ActividadDto getActividad(Long id);

}