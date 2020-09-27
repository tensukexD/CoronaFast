package edu.ulima.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ulima.prueba.model.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda,Long> {
    Tienda findByIdUsuario(Long idUsuario);
}