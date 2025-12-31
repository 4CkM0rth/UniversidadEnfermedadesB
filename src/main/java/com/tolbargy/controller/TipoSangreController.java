package com.tolbargy.controller;

import com.tolbargy.model.TipoSangre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tolbargy.service.ITipoSangreService;

import java.util.List;

@RestController
@RequestMapping("/api/tiposangre")
public class TipoSangreController {

    @Autowired
    private ITipoSangreService service;

    @GetMapping("")
    public List<TipoSangre> listarTodos() {
        return service.listarTodos();
    }
}
