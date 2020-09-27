package edu.ulima.prueba.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
//sheck
@Data
@Entity
@Table(name="TBVENDEDOR")
public class Vendedor extends Usuario {
    
    private OrdenCompra historialVentas[];
    
}