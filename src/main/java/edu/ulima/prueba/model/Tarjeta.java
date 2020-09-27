package edu.ulima.prueba.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBTARJETA")
public class Tarjeta {
  
    @Id
    private Long numeroTarjeta;

    private String idUsuario;
    private String tipoTarjeta;
    private String procesadoraPago;
    private String fechaVencimiento;
    private String banco;
    
}