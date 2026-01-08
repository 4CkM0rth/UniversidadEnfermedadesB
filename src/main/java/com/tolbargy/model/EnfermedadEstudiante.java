package com.tolbargy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enfermedad_estudiante")
public class EnfermedadEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    @JsonBackReference
    private Estudiante estudiante;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "observacion", nullable = false, length = 255)
    private String observacion;



}
