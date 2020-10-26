package edu.ulima.prueba.controllerRest;

import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edu.ulima.prueba.model.OrdenCompra;
import edu.ulima.prueba.repository.OrdenesRepository;

@RestController
@RequestMapping("/RevisarOrdenes")
public class ControladorOrdenCompra {
    private OrdenesRepository lRepository;

    @Autowired
    public ControladorOrdenCompra(OrdenesRepository lrepository) {
        this.lRepository=lrepository;
    }


@RequestMapping(value="ordenes/mostrar",method=RequestMethod.GET)
public ResponseEntity<List<OrdenCompra>> listartodaslasOrdenes( UriComponentsBuilder uri){
    List<OrdenCompra> listaordenes=lRepository.findAll();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<List<OrdenCompra>>(listaordenes, HttpStatus.OK);
    }
 @RequestMapping(value="ordenes/agregar",method=RequestMethod.POST)
    public ResponseEntity<Void> agregarUsuario(@RequestBody OrdenCompra newOrden,
                                                UriComponentsBuilder uri){
    
    
        OrdenCompra lg=lRepository.save(newOrden);
        lRepository.flush();
        Map<String,String> urlParams=new HashMap<>();
        urlParams.put("tipo","Usuario");
        urlParams.put("id",lg.getIdTransaccion().toString());
        HttpHeaders headers=new HttpHeaders();
        headers.setLocation(uri.path("/{tipo}/{id}").buildAndExpand(urlParams).toUri());   
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
@RequestMapping(value="ordenes/actualizarestado/{id}", method=RequestMethod.PUT)
    public ResponseEntity<OrdenCompra> editarTienda(@PathVariable("id") Long id,
                                                         @RequestBody OrdenCompra ordencompra){
            OrdenCompra current=null;
            Optional<OrdenCompra> l=lRepository.findById(id);
            
       
           System.out.println(id);
            if(l.isPresent()){
                current=l.get();
                current.setEstado(ordencompra.getEstado());
                
                lRepository.save(current);
                return new ResponseEntity<OrdenCompra>(current, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }}

 @RequestMapping(value="ordenesComprador/mostrar/{id}",method=RequestMethod.GET)
public ResponseEntity<List<OrdenCompra>> listarOrdenComprador( @PathVariable("id") Long id){
    List<OrdenCompra> listaordenes=lRepository.findByIdUsuarioComprador(id);

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<List<OrdenCompra>>(listaordenes, HttpStatus.OK);
    }   
 @RequestMapping(value="ordenesVendedor/mostrar/{id}",method=RequestMethod.GET)
    public ResponseEntity<List<OrdenCompra>> listarOrdenVendedor( @PathVariable("id") Long id){
        List<OrdenCompra> listaordenes=lRepository.findByIdUsuarioVendedor(id);
    
        //HttpHeaders headers=new HttpHeaders();
        //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
            
        return new ResponseEntity<List<OrdenCompra>>(listaordenes, HttpStatus.OK);
        }   
}
