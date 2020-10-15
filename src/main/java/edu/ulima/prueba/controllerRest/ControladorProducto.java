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

import edu.ulima.prueba.model.Comprador;
import edu.ulima.prueba.model.Producto;
import edu.ulima.prueba.repository.ProductoRepository;



@RestController
@RequestMapping("/revisarProducto/{id}")
public class ControladorProducto {
    private ProductoRepository lRepository;

    @Autowired
    public ControladorProducto(ProductoRepository lrepository) {
        this.lRepository=lrepository;
    }
//TIPO Usuario
@RequestMapping(value="/productos/mostrar",method=RequestMethod.GET)
public ResponseEntity<List<Producto>> listarUsuarios( UriComponentsBuilder uri, @PathVariable("id") Long id){
    List<Producto> listaProductos=lRepository.findAllByIdTienda(id);

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<List<Producto>>(listaProductos, HttpStatus.OK);
    }
    

@RequestMapping(value="productos/agregar",method=RequestMethod.POST)
public ResponseEntity<Void> agregarUsuario(@RequestBody Producto newProducto,
                                            UriComponentsBuilder uri){


    Producto lg=lRepository.save(newProducto);
    lRepository.flush();
    Map<String,String> urlParams=new HashMap<>();
    urlParams.put("tipo","Usuario");
    urlParams.put("id",lg.getIdUsuario().toString());
    HttpHeaders headers=new HttpHeaders();
    headers.setLocation(uri.path("/{tipo}/{id}").buildAndExpand(urlParams).toUri());   
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 //se debe usar el id del usuario para actualizar
 


 @RequestMapping(value="compradores/actualizar/{id}", method=RequestMethod.PUT)
 public ResponseEntity<Comprador> editarUsuarios(@PathVariable("id") Long id,
                                                  @RequestBody Comprador vendedor){
     Comprador current=null;
     Optional<Comprador> l=lRepository.findById(id);
     

    System.out.println(id);
     if(l.isPresent()){
         current=l.get();
         //current.setRol(vendedor.getRol());
         //current.setEstado(vendedor.getEstado());
         lRepository.save(current);
         return new ResponseEntity<Comprador>(current, HttpStatus.OK);
     }else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }}
 
             
@RequestMapping(value="compradores/eliminar/{id}", method=RequestMethod.DELETE)
public ResponseEntity<Void> eliminarUsuarios(@PathVariable("id") Long id){
    lRepository.deleteById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
}
    
@RequestMapping(value="compradores/seleccionar/{id}",method=RequestMethod.GET)
public ResponseEntity<Comprador> listarusuarioporid(@PathVariable("id") Long id){
    Comprador current=null;
    Optional<Comprador> l =lRepository.findById(id);
    current=l.get();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<Comprador>(current, HttpStatus.OK);
    }
}

/*@RequestMapping(value="usuarios/mostrar/{estado}",method=RequestMethod.GET)
public ResponseEntity<List<Vendedor>> ListarUsuariosporEstado(@PathVariable("estado") String estado){
    //List<Vendedor> listaUsuarios=lRepository.findByestado(estado);

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<List<Vendedor>>(listaUsuarios, HttpStatus.OK);
    }    



}*/   
