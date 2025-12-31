package com.tolbargy.controller;

import com.tolbargy.model.TipoIdentificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tolbargy.service.ITipoIdentificacionService;

import java.util.List;

@RestController
@RequestMapping("/api/tipoidentificacion")
public class TipoIdentificacionController {

    @Autowired
    private ITipoIdentificacionService service;

    @GetMapping("")
    public List<TipoIdentificacion> listarTodos() {
        return service.listarTodos();
    }

}