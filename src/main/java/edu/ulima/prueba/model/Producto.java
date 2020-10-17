package edu.ulima.prueba.model;
import java.io.File;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBPRODUCTOS")
public class Producto {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long idProducto;

    private Long idUsuario;
    private String imagen;//falta ver
    private String nombreProducto;
    private String categoria;
    private float precio;
    private int cantStock;
    private float puntuacion;
}