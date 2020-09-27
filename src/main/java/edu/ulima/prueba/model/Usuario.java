package edu.ulima.prueba.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
//sheck
@Data
@Entity
@Table(name="TBUSUARIOS")
public class Usuario {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long idUsuario;
    
    private String nombre;
    private String email;
    private String contrasena;
    private String telefono;
    
}