package edu.ulima.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ulima.prueba.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {
    Vendedor findByIdUsuario(Long idUsuario);
}