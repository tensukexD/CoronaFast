package edu.ulima.prueba.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
//sheck
@Data
@Entity
@Table(name="TBCOMPRADOR")
public class Comprador extends Usuario {
    private String distrito;
    private String direccion;
    private Tienda[] tiendasFavoritas;
    private OrdenCompra[] historialPedidos;
    private Producto[] carritoCompras;
    private Producto[] listaDeseados;
    private String NombreCompleto;
    
}