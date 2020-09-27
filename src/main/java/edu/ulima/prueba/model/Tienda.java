package edu.ulima.prueba.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBTIENDAS")
public class Tienda {

    
    @Id
    private Long idUsuario;
    
    private String RUC;
    private String nombreTienda;
    private String distrito;
    private String direccion;
    private String telefono;
    private OrdenCompra historialVentas[];
    
}