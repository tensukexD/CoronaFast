package edu.ulima.prueba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ulima.prueba.model.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda,Long> {
    Optional<Tienda> findByIdUsuario(Long idUsuario);
}