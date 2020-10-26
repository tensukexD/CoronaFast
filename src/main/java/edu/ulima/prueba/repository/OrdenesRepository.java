package edu.ulima.prueba.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ulima.prueba.model.OrdenCompra;


public interface OrdenesRepository extends JpaRepository<OrdenCompra,Long> {
    OrdenCompra findByIdTransaccion(long idTransaccion);
    List<OrdenCompra> findByIdUsuarioVendedor(Long idusuarioVendedor);
    List<OrdenCompra>findByIdUsuarioComprador(Long idusuarioComprador);

    
}
