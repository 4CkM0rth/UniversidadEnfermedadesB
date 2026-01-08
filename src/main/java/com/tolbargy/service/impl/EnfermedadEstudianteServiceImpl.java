package com.tolbargy.service.impl;

import com.tolbargy.model.EnfermedadEstudiante;
import com.tolbargy.repository.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tolbargy.repository.IEnfermedadEstudianteRepository;
import com.tolbargy.service.IEnfermedadEstudianteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnfermedadEstudianteServiceImpl implements IEnfermedadEstudianteService {

    @Autowired
    private IEnfermedadEstudianteRepository repo;
    private IEstudianteRepository repoEstudiante;

    @Override
    public List<EnfermedadEstudiante> listarTodos() {
        return repo.findAll();
    }

    @Override
    public EnfermedadEstudiante listarPorId(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void registrar(EnfermedadEstudiante entidad) {
        repo.save(entidad);
    }

    @Override
    public void actualizar(EnfermedadEstudiante entidad) {
        repo.save(entidad);
    }

    @Override
    public void eliminar(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<EnfermedadEstudiante> listarPorIdEstudiante(int idEstudiante) {
        return repo.listarPorIdEstudiante(idEstudiante);
    }

    @Transactional
    public void eliminar(Integer id) {
        repo.deleteByEstudianteId(id);
        repoEstudiante.deleteById(id);
    }


}