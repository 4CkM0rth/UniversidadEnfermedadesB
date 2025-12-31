package com.tolbargy.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_identificacion")
public class TipoIdentificacion {

    public TipoIdentificacion() {

    }

    public TipoIdentificacion(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

}
