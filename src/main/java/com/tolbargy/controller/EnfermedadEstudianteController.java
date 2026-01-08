package com.tolbargy.controller;

import com.tolbargy.dtos.EnfermedadesDTO;
import com.tolbargy.model.EnfermedadEstudiante;
import com.tolbargy.service.IEnfermedadEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enfermedad")
public class EnfermedadEstudianteController {

    @Autowired
    private IEnfermedadEstudianteService service;

    @GetMapping("")
    public List<EnfermedadesDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/listarPorIdEstudiante/{idEstudiante}")
    public List<EnfermedadesDTO> listarPorIdEstudiante(@PathVariable int idEstudiante) {
        return service.listarPorIdEstudiante(idEstudiante)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @PostMapping("")
    public void registrar(@RequestBody EnfermedadEstudiante enfermedadEstudiante) {
        service.registrar(enfermedadEstudiante);
    }

    private EnfermedadesDTO toDTO(EnfermedadEstudiante e) {
        return new EnfermedadesDTO(
                e.getId(),
                e.getNombre(),
                e.getObservacion(),
                e.getEstudiante().getId(),
                e.getEstudiante().getNombre(),
                e.getEstudiante().getApellido(),
                e.getEstudiante().getNumeroIdentificacion()
        );
    }
}
