package com.logistic.controller;

import com.logistic.dto.ActividadDto;
import com.logistic.dto.EmpleadoDto;
import com.logistic.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envio")
@CrossOrigin(origins = "*")
public class ActividadController {

    @Autowired
    private ActividadService service;

    /**
     * metodo para listar todos los envios de la base de datos.
     *
     * @return lista de envios guardados en la base de datos
     */
    @GetMapping("/")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    /**
     * Servicio para obtener a un envio por su id.
     * @param id id del envio
     * @return objeto con la información del envio
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEnvio(@PathVariable("id") Long id) {
        ActividadDto envio = service.obtenerEnvio(id);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envio);
    }

    /**
     * Metodo para insertar un envio en la base de datos.
     * @param envio datos del envio
     * @return objeto con los datos del envio ya almacenados
     */
    @PostMapping
    public ResponseEntity<?> insertarEnvio(@RequestBody ActividadDto envio) {
        envio = service.guardarEnvio(envio);
        if (envio == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(envio);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        boolean usuario = service.eliminarActividad(id);
        if(usuario == true){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ActividadDto u){
        ActividadDto p = service.getActividad(id);
        if(p == null){
            return ResponseEntity.noContent().build();
        }
        p = service.guardarEnvio(u);
        if(p == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(p);
    }
}

