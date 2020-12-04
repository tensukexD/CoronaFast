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
import edu.ulima.prueba.model.Tienda;
import edu.ulima.prueba.repository.CompradorRepository;



@RestController
@RequestMapping("/revisarCompradores")
public class ControladorComprador {
    private CompradorRepository lRepository;

    @Autowired
    public ControladorComprador(CompradorRepository lrepository) {
        this.lRepository=lrepository;
    }
//TIPO Usuario
@RequestMapping(value="compradores/mostrar",method=RequestMethod.GET)
public ResponseEntity<List<Comprador>> listarUsuarios( UriComponentsBuilder uri){
    List<Comprador> listaCompradores=lRepository.findAll();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<List<Comprador>>(listaCompradores, HttpStatus.OK);
    }
    

@RequestMapping(value="compradores/agregar",method=RequestMethod.POST)
public ResponseEntity<Void> agregarUsuario(@RequestBody Comprador newComprador,
                                            UriComponentsBuilder uri){


    Comprador lg=lRepository.save(newComprador);
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
                                                  @RequestBody Comprador comprador){
     Comprador current=null;
     Optional<Comprador> l=lRepository.findById(id);
     

    System.out.println(id);
    if(l.isPresent()){
        current=l.get();
        current.setNombre(comprador.getNombre());
        current.setDistrito(comprador.getDistrito());
        current.setTelefono(comprador.getTelefono());
        current.setTiendasFavoritas(comprador.getTiendasFavoritas()); 
        current.setCarritoCompras(comprador.getCarritoCompras()); 
        current.setDireccion(comprador.getDireccion());
        current.setCategoriaPreferida(comprador.getCategoriaPreferida());
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

@RequestMapping(value = "compradores/{idComprador}/agregarTienda/{idTienda}", method = RequestMethod.PUT)
public ResponseEntity<Void> agregarTiendaFav(@PathVariable("idComprador") Long idComprador, @PathVariable("idTienda") Long idTienda){

    Comprador comprador = null;

    Optional<Comprador> l = lRepository.findById(idComprador);
    comprador=l.get();
    ArrayList<Long> tiendas = comprador.getTiendasFavoritas();

    if(tiendas.contains(idTienda)){
        System.out.println("Ya existe esta tienda");
    }else{
        tiendas.add(idTienda);
    }

    comprador.setTiendasFavoritas(tiendas);

    lRepository.save(comprador);
    return new ResponseEntity<Void>(HttpStatus.OK);
}

@RequestMapping(value = "compradores/{idComprador}/agregarProducto/{idProducto}", method = RequestMethod.PUT)
public ResponseEntity<Void> agregarProductoFav(@PathVariable("idComprador") Long idComprador, @PathVariable("idProducto") Long idProducto){

    Comprador comprador = null;

    Optional<Comprador> l = lRepository.findById(idComprador);
    comprador=l.get();
    ArrayList<Long> productos = comprador.getListaDeseados();

    if(productos.contains(idProducto)){
        System.out.println("Ya existe este producto");
    }else{
        productos.add(idProducto);
    }

    comprador.setListaDeseados(productos);

    lRepository.save(comprador);
    return new ResponseEntity<Void>(HttpStatus.OK);
}

@RequestMapping(value = "compradores/{idComprador}/agregarCarrito/{idProducto}", method = RequestMethod.PUT)
public ResponseEntity<Void> agregarCarrito(@PathVariable("idComprador") Long idComprador, @PathVariable("idProducto") Long idProducto){

    Comprador comprador = null;

    Optional<Comprador> l = lRepository.findById(idComprador);
    comprador=l.get();

    ArrayList<Long> carrito = comprador.getCarritoCompras();

    if(carrito.contains(idProducto)){
        System.out.println("Ya existe este producto");
    }else{
        carrito.add(idProducto);
    }

    comprador.setCarritoCompras(carrito);

    lRepository.save(comprador);
    return new ResponseEntity<Void>(HttpStatus.OK);
}

@RequestMapping(value = "compradores/{idComprador}/eliminarCarrito/{idProducto}", method = RequestMethod.PUT)
public ResponseEntity<Void> eliminarCarrito(@PathVariable("idComprador") Long idComprador, @PathVariable("idProducto") Long idProducto){

    Comprador comprador = null;

    Optional<Comprador> l = lRepository.findById(idComprador);
    comprador=l.get();

    ArrayList<Long> carrito = comprador.getCarritoCompras();

    for(int i=0; i<carrito.size(); i++){
        System.out.println(carrito.get(i));
        System.out.println(idProducto);
        System.out.println(carrito.size());
        if(String.valueOf(carrito.get(i)).equals(String.valueOf(idProducto))){
            carrito.remove(i);
            System.out.println(carrito.size());
            
        }
        System.out.println(carrito.size());
    }

    comprador.setCarritoCompras(carrito);

    lRepository.save(comprador);
    return new ResponseEntity<Void>(HttpStatus.OK);
    
}

@RequestMapping(value = "compradores/{idComprador}/eliminarTienda/{idTienda}", method = RequestMethod.PUT)
public ResponseEntity<Void> eliminarTienda(@PathVariable("idComprador") Long idComprador, @PathVariable("idTienda") Long idTienda){

    Comprador comprador = null;

    Optional<Comprador> l = lRepository.findById(idComprador);
    comprador=l.get();

    ArrayList<Long> tiendas = comprador.getTiendasFavoritas();

    for(int i=0; i<tiendas.size(); i++){
        System.out.println(tiendas.get(i));
        System.out.println(idTienda);
        System.out.println(tiendas.size());
        if(String.valueOf(tiendas.get(i)).equals(String.valueOf(idTienda))){
            tiendas.remove(i);
            System.out.println(tiendas.size());
        }
        System.out.println(tiendas.size());
    }

    comprador.setTiendasFavoritas(tiendas);

    lRepository.save(comprador);
    return new ResponseEntity<Void>(HttpStatus.OK);
}

@RequestMapping(value = "compradores/{idComprador}/eliminarProducto/{idProducto}", method = RequestMethod.PUT)
public ResponseEntity<Void> eliminarProducto(@PathVariable("idComprador") Long idComprador, @PathVariable("idProducto") Long idProducto){

    Comprador comprador = null;

    Optional<Comprador> l = lRepository.findById(idComprador);
    comprador=l.get();

    ArrayList<Long> productos = comprador.getListaDeseados();

    for(int i=0; i<productos.size(); i++){
        System.out.println(productos.get(i));
        System.out.println(idProducto);
        System.out.println(productos.size());
        if(String.valueOf(productos.get(i)).equals(String.valueOf(idProducto))){
            productos.remove(i);
            System.out.println(productos.size());
        }
        System.out.println(productos.size());
    }

    comprador.setListaDeseados(productos);

    lRepository.save(comprador);
    return new ResponseEntity<Void>(HttpStatus.OK);
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

    



 

