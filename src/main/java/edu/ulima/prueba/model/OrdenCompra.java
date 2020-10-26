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
    private Long idUsuarioVendedor;
    private Long idUsuarioComprador;
    private String direccionexacta;
    private Long idProducto;
    private String nota;
    private String fechaCompra;
    private int cantidad;
    private String estado;
    private float total;
    
}