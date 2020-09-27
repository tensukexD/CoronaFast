package edu.ulima.prueba.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="pedidosencarrito")
public class Pedidoenelcarrito {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long idTransaccion;

    private String idComprador;
    
    private String idProducto;
    private String idVendedor;
    private int  cantidadacomprar;
    private String estado;//se compro o no
  
    private float total;
    
}
