package com.tolbargy.controller;

import com.tolbargy.dtos.TipoIdentificacionDTO;
import com.tolbargy.model.TipoIdentificacion;
import com.tolbargy.service.ITipoIdentificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoidentificacion")
public class TipoIdentificacionController {

    @Autowired
    private ITipoIdentificacionService service;

    @GetMapping("")
    public List<TipoIdentificacionDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private TipoIdentificacionDTO toDTO(TipoIdentificacion t) {
        return new TipoIdentificacionDTO(
                t.getId(),
                t.getNombre()
        );
    }
}
