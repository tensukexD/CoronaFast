package edu.ulima.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ulima.prueba.model.Comprador;

public interface CompradorRepository extends JpaRepository<Comprador,Long> {
    Comprador findByIdUsuario(long idUsuario);
}