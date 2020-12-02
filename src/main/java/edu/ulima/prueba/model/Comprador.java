package edu.ulima.prueba.model;

import java.util.ArrayList;

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
    private ArrayList<Long> tiendasFavoritas = new ArrayList<Long>();
    private ArrayList<Long> carritoCompras = new ArrayList<Long>();
    private ArrayList<Long> listaDeseados = new ArrayList<Long>();
    private String categoriaPreferida;
}