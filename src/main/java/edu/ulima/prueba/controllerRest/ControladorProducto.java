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

import edu.ulima.prueba.model.Producto;
import edu.ulima.prueba.repository.ProductoRepository;


@RestController
//revisar todos los productos
@RequestMapping("/revisarProductos")
public class ControladorProducto {
    private ProductoRepository lRepository;

    @Autowired
    public ControladorProducto(ProductoRepository lrepository) {
        this.lRepository=lrepository;
}

@RequestMapping(value="productos/mostrar",method=RequestMethod.GET)
public ResponseEntity<List<Producto>> listarProductos( UriComponentsBuilder uri){
    List<Producto> listaproductos= lRepository.findAll();

    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
        
    return new ResponseEntity<List<Producto>>(listaproductos, HttpStatus.OK);
    }

@RequestMapping(value="productosTienda/{id}/mostrar",method=RequestMethod.GET)
public ResponseEntity<List<Producto>> productosPorTienda(@PathVariable("id") Long id){

    List<Producto> listaProductos = lRepository.findByIdUsuario(id);

    return new ResponseEntity<List<Producto>>(listaProductos, HttpStatus.OK);
}

@RequestMapping(value="productos/agregar",method=RequestMethod.POST)
public ResponseEntity<Void> agregarProducto(@RequestBody Producto newProducto,
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

    @RequestMapping(value="productos/actualizar/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Producto> editarProducto(@PathVariable("id") Long id,
                                                     @RequestBody Producto producto){
        Producto current=null;
        Optional<Producto> l = lRepository.findByIdProducto(id);
        
   
       System.out.println(id);
        if(l.isPresent()) {
            current=l.get();
            current.setNombreProducto(producto.getNombreProducto());
            current.setCategoria(producto.getCategoria());
            current.setPrecio(producto.getPrecio());
            current.setCantStock(producto.getCantStock());
            current.setPuntuacion(producto.getPuntuacion());
            lRepository.save(current);
            return new ResponseEntity<Producto>(current, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }}

    @RequestMapping(value="productos/eliminar/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> eliminarUsuarios(@PathVariable("id") Long id){
        lRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


@RequestMapping(value="producto/seleccionar/{categorias}",method=RequestMethod.GET)
public ResponseEntity<List<Producto>> listarCategoria(@PathVariable("categorias") String categoria){
    
    List<Producto>  l =lRepository.findBycategoria(categoria);
    
    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
            
    return new ResponseEntity<List<Producto>>(l, HttpStatus.OK);
    }

/*@RequestMapping(value="producto/seleccionar/{usuarios}",method=RequestMethod.GET)
public ResponseEntity<List<Producto>> listarUsuario(@PathVariable("usuarios") Long idUsuario){
    
    List<Producto>  l =lRepository.findByIdUsuario(idUsuario);
    
    //HttpHeaders headers=new HttpHeaders();
    //headers.setLocation(uri.path("/usuarios/mostrar").buildAndExpand().toUri()); 
            
    return new ResponseEntity<List<Producto>>(l, HttpStatus.OK);
    }*/
}



