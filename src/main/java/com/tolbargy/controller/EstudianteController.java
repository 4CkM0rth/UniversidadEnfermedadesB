package com.tolbargy.controller;

import com.tolbargy.dtos.EnfermedadesDTO;
import com.tolbargy.dtos.EstudianteDTO;
import com.tolbargy.dtos.TipoIdentificacionDTO;
import com.tolbargy.dtos.TipoSangreDTO;
import com.tolbargy.model.Estudiante;
import com.tolbargy.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private IEstudianteService service;

    @GetMapping("")
    public List<EstudianteDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("{id}")
    public EstudianteDTO listarPorId(@PathVariable int id) {
        Estudiante estudiante = service.listarPorId(id);
        return toDTO(estudiante);
    }

    @PostMapping("")
    public void registrar(@RequestBody Estudiante estudiante) {
        service.registrar(estudiante);
    }

    @PutMapping("")
    public void actualizar(@RequestBody Estudiante estudiante) {
        service.actualizar(estudiante);
    }

    @DeleteMapping("{id}")
    public void eliminar(@PathVariable int id) {
        service.eliminar(id);
    }

    private EstudianteDTO toDTO(Estudiante est) {

        TipoIdentificacionDTO tipoIdentificacionDTO =
                new TipoIdentificacionDTO(
                        est.getTipoIdentificacion().getId(),
                        est.getTipoIdentificacion().getNombre()
                );

        TipoSangreDTO tipoSangreDTO =
                new TipoSangreDTO(
                        est.getTipoSangre().getId(),
                        est.getTipoSangre().getNombre()
                );

        List<EnfermedadesDTO> enfermedades = est.getEnfermedades()
                .stream()
                .map(e -> new EnfermedadesDTO(
                        e.getId(),
                        e.getNombre(),
                        e.getObservacion(),
                        est.getId(),
                        est.getNombre(),
                        est.getApellido(),
                        est.getNumeroIdentificacion()
                ))
                .toList();

        return new EstudianteDTO(
                est.getId(),
                est.getNumeroIdentificacion(),
                est.getNombre(),
                est.getApellido(),
                est.getFechaNacimiento(),
                tipoIdentificacionDTO,
                tipoSangreDTO,
                enfermedades
        );
    }


}