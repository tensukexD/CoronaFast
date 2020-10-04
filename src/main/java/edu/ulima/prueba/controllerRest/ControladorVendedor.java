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
import edu.ulima.prueba.model.Vendedor;
import edu.ulima.prueba.repository.VendedorRepository;


@RestController
@RequestMapping("/revisarVendedores")
public class ControladorVendedor {
    private VendedorRepository lRepository;

    @Autowired
    public ControladorVendedor(VendedorRepository lrepository) {
        this.lRepository=lrepository;
    }
//TIPO Usuario
@RequestMapping(value="vendedores/mostrar",method=RequestMethod.GET)
public ResponseEntity<List<Vendedor>> listarUsuarios( UriComponentsBuilder uri){
    List<Vendedor> listaVendedores=lRepository.findAll();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<List<Vendedor>>(listaVendedores, HttpStatus.OK);
    }
    

@RequestMapping(value="vendedores/agregar",method=RequestMethod.POST)
public ResponseEntity<Void> agregarUsuario(@RequestBody Vendedor newVendedor,
                                            UriComponentsBuilder uri){


    Vendedor lg=lRepository.save(newVendedor);
    lRepository.flush();
    Map<String,String> urlParams=new HashMap<>();
    urlParams.put("tipo","Usuario");
    urlParams.put("id",lg.getIdUsuario().toString());
    HttpHeaders headers=new HttpHeaders();
    headers.setLocation(uri.path("/{tipo}/{id}").buildAndExpand(urlParams).toUri());   
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 //se debe usar el id del usuario para actualizar
 


 @RequestMapping(value="vendedores/actualizar/{id}", method=RequestMethod.PUT)
 public ResponseEntity<Vendedor> editarUsuarios(@PathVariable("id") Long id,
                                                  @RequestBody Vendedor vendedor){
     Vendedor current=null;
     Optional<Vendedor> l=lRepository.findById(id);
     

    System.out.println(id);
     if(l.isPresent()){
         current=l.get();
         //current.setRol(vendedor.getRol());
         //current.setEstado(vendedor.getEstado());
         lRepository.save(current);
         return new ResponseEntity<Vendedor>(current, HttpStatus.OK);
     }else{
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }}
 
             
@RequestMapping(value="vendedores/eliminar/{id}", method=RequestMethod.DELETE)
public ResponseEntity<Void> eliminarUsuarios(@PathVariable("id") Long id){
    lRepository.deleteById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
}
    
@RequestMapping(value="vendedores/seleccionar/{id}",method=RequestMethod.GET)
public ResponseEntity<Vendedor> listarusuarioporid(@PathVariable("id") Long id){
    Vendedor current=null;
    Optional<Vendedor> l =lRepository.findById(id);
    current=l.get();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<Vendedor>(current, HttpStatus.OK);
    }

@RequestMapping(value="vendedores/seleccionarEmail/{email}",method=RequestMethod.GET)
public ResponseEntity<Vendedor> findUsuarioByEmail(@PathVariable("email") String email){
    Vendedor current=null;
    Optional<Vendedor> l = lRepository.findByEmail(email);
    current=l.get();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<Vendedor>(current, HttpStatus.OK);
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

    



 

