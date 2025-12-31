package com.tolbargy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero_identificacion", nullable = false, length = 30, unique = true)
    private String numeroIdentificacion;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_identificacion", nullable = false)
    private TipoIdentificacion tipoIdentificacion;

    @ManyToOne
    @JoinColumn(name = "id_tipo_sangre", nullable = false)
    private TipoSangre tipoSangre;

}