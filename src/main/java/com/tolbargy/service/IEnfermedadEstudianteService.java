package com.tolbargy.service;

import com.tolbargy.model.EnfermedadEstudiante;

import java.util.List;

public interface IEnfermedadEstudianteService extends ICrudService<EnfermedadEstudiante>{

    public List<EnfermedadEstudiante> listarPorIdEstudiante(int idEstudiante);
}
