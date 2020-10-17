package edu.ulima.prueba.controllerRest;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edu.ulima.prueba.model.Tienda;
import edu.ulima.prueba.repository.TiendaRepository;



@RestController
@RequestMapping("/revisarTienda")
public class ControladorTienda {
    private TiendaRepository lRepository;

    @Autowired
    public ControladorTienda(TiendaRepository lrepository) {
        this.lRepository=lrepository;
    }
//TIPO Usuario
@RequestMapping(value="tiendas/mostrar",method=RequestMethod.GET)
public ResponseEntity<List<Tienda>> listarTiendas( UriComponentsBuilder uri){
    List<Tienda> listatiendas=lRepository.findAll();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<List<Tienda>>(listatiendas, HttpStatus.OK);
    }
    

@RequestMapping(value="tiendas/agregar",method=RequestMethod.POST)
public ResponseEntity<Void> agregarTienda(@RequestBody Tienda newTienda,
                                            UriComponentsBuilder uri){


    Tienda lg=lRepository.save(newTienda);
    lRepository.flush();
    Map<String,String> urlParams=new HashMap<>();
    urlParams.put("tipo","Usuario");
    urlParams.put("id",lg.getIdUsuario().toString());
    HttpHeaders headers=new HttpHeaders();
    headers.setLocation(uri.path("/{tipo}/{id}").buildAndExpand(urlParams).toUri());   
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 //se debe usar el id del usuario para actualizar
 


 @RequestMapping(value="tienda/actualizar/{id}", method=RequestMethod.PUT)
 public ResponseEntity<Tienda> editarTienda(@PathVariable("id") Long id,
                                                  @RequestBody Tienda tienda){
     Tienda current=null;
     Optional<Tienda> l=lRepository.findByIdUsuario(id);
     

    System.out.println(id);
     if(l.isPresent()){
         current=l.get();
         current.setRUC(tienda.getRUC());
         current.setNombreTienda(tienda.getNombreTienda());
         current.setDistrito(tienda.getDistrito());
         current.setDireccion(tienda.getDireccion());
         current.setTelefono(tienda.getTelefono());
         lRepository.save(current);
         return new ResponseEntity<Tienda>(current, HttpStatus.OK);
     }else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }}
 
             
@RequestMapping(value="tienda/eliminar/{id}", method=RequestMethod.DELETE)
public ResponseEntity<Void> eliminarTienda(@PathVariable("id") Long id){
    lRepository.deleteById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
}
    
@RequestMapping(value="tienda/seleccionar/{id}",method=RequestMethod.GET)
public ResponseEntity<Tienda> listarTiendaId(@PathVariable("id") Long id){
    Tienda current=null;
    Optional<Tienda> l =lRepository.findById(id);
    current=l.get();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<Tienda>(current, HttpStatus.OK);
    }
}