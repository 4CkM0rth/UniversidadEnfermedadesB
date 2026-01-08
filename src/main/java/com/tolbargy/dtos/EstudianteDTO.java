package com.tolbargy.dtos;

import java.time.LocalDate;
import java.util.List;

public record EstudianteDTO(
        int id,
        String numeroIdentificacion,
        String nombre,
        String apellido,
        LocalDate fechaNacimiento,
        TipoIdentificacionDTO tipoIdentificacion,
        TipoSangreDTO tipoSangre,
        List<EnfermedadesDTO> enfermedades
) {}
