package com.tolbargy.controller;

import com.tolbargy.model.EnfermedadEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tolbargy.service.IEnfermedadEstudianteService;

import java.util.List;

@RestController
@RequestMapping("/api/enfermedad-estudiante")
public class EnfermedadEstudianteController {

    @Autowired
    private IEnfermedadEstudianteService service;

    @GetMapping("")
    public List<EnfermedadEstudiante> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping("")
    public void registrar(@RequestBody EnfermedadEstudiante enfermedadEstudiante) {
        service.registrar(enfermedadEstudiante);
    }

    @GetMapping("/listarPorIdEstudiante/{idEstudiante}")
    public List<EnfermedadEstudiante> listarPorIdEstudiante(@PathVariable int idEstudiante) {
        return service.listarPorIdEstudiante(idEstudiante);
    }

}
