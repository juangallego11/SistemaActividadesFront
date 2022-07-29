package com.logistic.service.impl;

import com.logistic.dto.ActividadDto;
import com.logistic.dto.EmpleadoDto;
import com.logistic.entity.Actividad;
import com.logistic.entity.Empleado;
import com.logistic.exceptions.responses.BadRequestException;
import com.logistic.repository.ActividadRepository;
import com.logistic.service.ActividadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadServiceImpl implements ActividadService {

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private ActividadRepository repository;


    @Override
    public ActividadDto guardarEnvio(ActividadDto envio) {
        ActividadDto sh = mapper.map(repository.save(mapper.map(envio, Actividad.class)), ActividadDto.class);
        sh.setDiasRetraso(this.obtenerDiasRetraso(sh));
        return sh;
    }

    public int obtenerDiasRetraso(ActividadDto c){
        Date fechaactual = new Date(System.currentTimeMillis());
        int milisecondsByDay = 86400000;
        Integer dias = (int) ((fechaactual.getTime()-c.getFechaRegistro().getTime()) / milisecondsByDay);
       return(dias > 0 ? dias : 0);
    }

    @Override
    public ActividadDto obtenerEnvio(Long idEnvio) {
        Optional<Actividad> envioOptional = repository.findById(idEnvio);
        if (envioOptional.isPresent()) {
            return mapper.map(envioOptional.get(), ActividadDto.class);
        }
        throw new BadRequestException("Ocurrio un error al obtener un envio");
    }

    @Override
    public List<ActividadDto> obtenerTodos() {
        List<ActividadDto> respuesta = new ArrayList<>();
        List<Actividad> envios = repository.findAll();
        if (envios != null) {
            for (Actividad c : envios) {
                ActividadDto res = new ActividadDto();
                res = mapper.map(c, ActividadDto.class);
                res.setDiasRetraso(this.obtenerDiasRetraso(res));
                respuesta.add(res);
            }
            return respuesta;
        }
        throw new BadRequestException("Ocurrio un error de listar los envios");
    }

    @Override
    public Boolean eliminarActividad(Long id) {
        if (id != null) {
            Optional<Actividad> u = repository.findById(id);
            if (u.isPresent()) {
                repository.delete(u.get());
                return true;
            }
        }
        return false;
    }


    @Override
    public ActividadDto getActividad(Long id) {
        Optional<Actividad> aOptional = repository.findById(id);
        if (aOptional.isPresent()) {
            return mapper.map(aOptional.get(), ActividadDto.class);
        }
        throw new BadRequestException("Ocurrio un error al obtener una actividad");
    }
}
