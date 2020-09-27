package edu.ulima.prueba.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
//no se si servira xd

@Data
@Entity
@Table(name="TBODERNESCOMPRA")
public class OrdenCompra {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long idTransaccion;

    private String idUsuarioComprador;
    
    private String idProducto;
    private String resena;
    private String fechaCompra;
    private Producto totalProductos[];
    private String estado;
    private int total;
    
}