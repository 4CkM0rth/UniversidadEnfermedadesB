package com.tolbargy.controller;

import com.tolbargy.dtos.TipoSangreDTO;
import com.tolbargy.model.TipoSangre;
import com.tolbargy.service.ITipoSangreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiposangre")
public class TipoSangreController {

    @Autowired
    private ITipoSangreService service;

    @GetMapping("")
    public List<TipoSangreDTO> listarTodos() {
        return service.listarTodos()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private TipoSangreDTO toDTO(TipoSangre t) {
        return new TipoSangreDTO(
                t.getId(),
                t.getNombre()
        );
    }
}
