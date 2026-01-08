package com.tolbargy.dtos;

public record EnfermedadesDTO(
        int id,
        String nombre,
        String observacion,
        int estudianteId,
        String estudianteNombre,
        String estudianteApellido,
        String estudianteNumeroIdentificacion
) {}
