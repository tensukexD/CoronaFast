package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Producto;
import edu.ulima.prueba.model.Tienda;

@Controller
@RequestMapping("/CompradorPaginaVendedor")
public class CompradorPaginaTiendaVendedor {
    @GetMapping(value="/{idUsuario}")
    public String Retornarpag( HttpServletRequest req,Model model, @PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos=Arrays.asList(productos);
        model.addAttribute("listaProductos", listaProductos);

        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());

        return "Comprador-PaginaTiendaVendedor";
    }

    @GetMapping(value = "/agregarTienda/{id}")
    public String tiendaFavEnPag(HttpServletRequest req,Model model,@PathVariable("id") Long idTienda){

        String idUsuario = (String) req.getSession().getAttribute("idingresado");

        RestTemplate rest = new RestTemplate();

        Tienda t  = new Tienda();

        String url = "http://localhost:8080/revisarCompradores/compradores/"+idUsuario+"/agregarTienda/"+idTienda;

        rest.put(url, t, Tienda.class);

        return "redirect:/CompradorPaginaVendedor/"+idTienda;
    }

    /*@PostMapping(value = "/agregar/{id}")
    public String agregarTiendaFav(HttpServletRequest req,Model model,@PathVariable("id") Long idTienda){

        String idUsuario = (String) req.getSession().getAttribute("idingresado");

        RestTemplate rest = new RestTemplate();

        Tienda t  = new Tienda();

        String url = "http://localhost:8080/revisarCompradores/compradores/"+idUsuario+"/agregarTienda/"+idTienda;

        rest.put(url, t, Tienda.class);

        return "redirect:/CompradorPaginaVendedor/";
    }*/

    @GetMapping(value = "/{idUsuario}/comida")
    public String tiendaComida(HttpServletRequest req,Model model,@PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos= new ArrayList<Producto>();
        
        for(Producto p : productos){
            if(p.getCategoria().equalsIgnoreCase("comida")){
                listaProductos.add(p);
            }
        }

        model.addAttribute("listaProductos", listaProductos);


        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());


        return "Comprador-PaginaTiendaVendedor";
    }

    @GetMapping(value = "/{idUsuario}/electronicos")
    public String tiendaElectronicos(HttpServletRequest req,Model model,@PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos2 = new ArrayList<Producto>();
        
        for(Producto p : productos){
            if(p.getCategoria().equalsIgnoreCase("electronicos")){
                listaProductos2.add(p);
            }
        }

        model.addAttribute("listaProductos", listaProductos2);


        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());


        return "Comprador-PaginaTiendaVendedor";
    }

    @GetMapping(value = "/{idUsuario}/accesorios")
    public String tiendaAccesorios(HttpServletRequest req,Model model,@PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos= new ArrayList<Producto>();
        
        for(Producto p : productos){
            if(p.getCategoria().equalsIgnoreCase("accesorios")){
                listaProductos.add(p);
            }
        }

        model.addAttribute("listaProductos", listaProductos);


        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());


        return "Comprador-PaginaTiendaVendedor";
    }

    @GetMapping(value = "/{idUsuario}/ropa")
    public String tiendaRopa(HttpServletRequest req,Model model,@PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos= new ArrayList<Producto>();
        
        for(Producto p : productos){
            if(p.getCategoria().equalsIgnoreCase("ropa")){
                listaProductos.add(p);
            }
        }

        model.addAttribute("listaProductos", listaProductos);


        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());


        return "Comprador-PaginaTiendaVendedor";
    }

    @GetMapping(value = "/{idUsuario}/medicina")
    public String tiendaMedicina(HttpServletRequest req,Model model,@PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos= new ArrayList<Producto>();
        
        for(Producto p : productos){
            if(p.getCategoria().equalsIgnoreCase("medicina")){
                listaProductos.add(p);
            }
        }

        model.addAttribute("listaProductos", listaProductos);


        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());


        return "Comprador-PaginaTiendaVendedor";
    }

    @GetMapping(value = "/{idUsuario}/juguetes")
    public String tiendaJuguetes(HttpServletRequest req,Model model,@PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos= new ArrayList<Producto>();
        
        for(Producto p : productos){
            if(p.getCategoria().equalsIgnoreCase("juguetes")){
                listaProductos.add(p);
            }
        }

        model.addAttribute("listaProductos", listaProductos);


        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());


        return "Comprador-PaginaTiendaVendedor";
    }

    @GetMapping(value = "/{idUsuario}/limpieza")
    public String tiendaLimpieza(HttpServletRequest req,Model model,@PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos= new ArrayList<Producto>();
        
        for(Producto p : productos){
            if(p.getCategoria().equalsIgnoreCase("limpieza")){
                listaProductos.add(p);
            }
        }

        model.addAttribute("listaProductos", listaProductos);


        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());


        return "Comprador-PaginaTiendaVendedor";
    }

    @GetMapping(value = "/{idUsuario}/utilesOficina")
    public String tiendaUtilesDeOficina(HttpServletRequest req,Model model,@PathVariable("idUsuario") Long idUsuario){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        String link="http://localhost:8080/revisarProductos/productosTienda/"+idUsuario+"/mostrar";
        ResponseEntity<Producto[]>productosTienda=rest.getForEntity(link,Producto[].class); 
        
        Producto[] productos = productosTienda.getBody();
        List<Producto> listaProductos= new ArrayList<Producto>();
        
        for(Producto p : productos){
            if(p.getCategoria().trim().equalsIgnoreCase("utilesOficina".trim())){
                listaProductos.add(p);
            }
        }

        model.addAttribute("listaProductos", listaProductos);


        String link2 = "http://localhost:8080/revisarTienda/tienda/seleccionar/"+idUsuario;
        ResponseEntity<Tienda> tienda = rest.getForEntity(link2, Tienda.class);

        model.addAttribute("tienda", tienda.getBody());


        return "Comprador-PaginaTiendaVendedor";
    }
}
