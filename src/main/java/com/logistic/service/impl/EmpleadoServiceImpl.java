package com.logistic.service.impl;

import com.logistic.dto.EmpleadoDto;
import com.logistic.entity.Empleado;
import com.logistic.exceptions.responses.BadRequestException;
import com.logistic.repository.EmpleadoRepository;
import com.logistic.service.EmpleadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public EmpleadoDto guardarCliente(EmpleadoDto cliente) {
        EmpleadoDto sh = mapper.map(repository.save(mapper.map(cliente, Empleado.class)), EmpleadoDto.class);
        return sh;
    }


    @Override
    public EmpleadoDto getCliente(Long idCliente) {
        Optional<Empleado> clienteOptional = repository.findById(idCliente);
        if (clienteOptional.isPresent()) {
            return mapper.map(clienteOptional.get(), EmpleadoDto.class);
        }
        throw new BadRequestException("Ocurrio un error al obtener un cliente");
    }

    @Override
    public List<EmpleadoDto> getClientes() {
        List<EmpleadoDto> respuesta = new ArrayList<>();
        List<Empleado> clientes = repository.findAll();
        if (clientes != null) {
            for (Empleado c : clientes) {
                respuesta.add(mapper.map(c, EmpleadoDto.class));
            }
            return respuesta;
        }
        throw new BadRequestException("Ocurrio un error de listar los clientes");
    }

    @Override
    public boolean eliminarUsuario(Long id) {
        if (id != null) {
            Optional<Empleado> u = repository.findById(id);
            if (u.isPresent()) {
                repository.delete(u.get());
                return true;
            }
        }
        return false;
    }

}
