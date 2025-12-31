package com.tolbargy.repository;

import com.tolbargy.model.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Integer> {

}
