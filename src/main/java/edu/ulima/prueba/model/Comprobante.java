package edu.ulima.prueba.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
//sialcanzaeltiempo lo hacemos
@Data
@Entity
@Table(name="TBCOMPROBANTES")
public class Comprobante {

    @Id
    private Long idComprobante;

    private String idTransaccion;
    private String tipo;
    private String RUC;
    private int total;
    
}