package com.logistic.controller;

import com.logistic.dto.EmpleadoDto;
import com.logistic.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    /**
     * metodo para listar todos los clientes de la base de datos.
     *
     * @return lista de clientes guardados en la base de datos
     */
    @GetMapping("/")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(service.getClientes());
    }

    /**
     * Servicio para obtener a un cliente por su id.
     * @param id id del cliente
     * @return objeto con la información del cliente
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") Long id) {
        EmpleadoDto cliente = service.getCliente(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    /**
     * Metodo para insertar un cliente en la base de datos.
     * @param cliente datos del cliente
     * @return objeto con los datos del clientes ya almacenados
     */
    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody EmpleadoDto cliente) {
        cliente = service.guardarCliente(cliente);
        if (cliente == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cliente);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        boolean usuario = service.eliminarUsuario(id);
        if(usuario == true){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody EmpleadoDto u){
        EmpleadoDto p = service.getCliente(id);
        if(p == null){
            return ResponseEntity.noContent().build();
        }
        p = service.guardarCliente(u);
        if(p == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(p);
    }

}
