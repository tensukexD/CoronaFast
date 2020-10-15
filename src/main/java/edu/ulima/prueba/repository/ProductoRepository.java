package edu.ulima.prueba.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.ulima.prueba.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByIdProducto(Long idProducto);
    List<Producto> findAllByIdTienda(Long idTienda);
}