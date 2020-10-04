package edu.ulima.prueba.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ulima.prueba.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {
    Optional<Vendedor> findByEmail(String email);
    Vendedor findByIdUsuario(Long idUsuario);
}