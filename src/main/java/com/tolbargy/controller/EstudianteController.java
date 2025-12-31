package com.tolbargy.controller;

import com.tolbargy.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tolbargy.service.IEstudianteService;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private IEstudianteService service;

    @GetMapping("")
    public List<Estudiante> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("{id}")
    public Estudiante listarPorId(@PathVariable int id) {
        return service.listarPorId(id);
    }

    @PostMapping("")
    public void registrar(@RequestBody Estudiante entidad) {
        service.registrar(entidad);
    }

    @PutMapping("")
    public void actualizar(@RequestBody Estudiante entidad) {
        service.actualizar(entidad);
    }

    @DeleteMapping("{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }

}