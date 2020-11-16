package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Producto;
import edu.ulima.prueba.model.Tienda;

@Controller
@RequestMapping("/ResultadosBusqueda")
public class ResultadosBusqueda {

    String str;

    @GetMapping(value = "/")
    public String GetIndex(Model model, HttpServletRequest req){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest = new RestTemplate();
        String link = "http://coronafast.herokuapp.com/revisarProductos/productos/mostrar";
        ResponseEntity<Producto[]> listaProductos = rest.getForEntity(link, Producto[].class);
        Producto[] arrPro = listaProductos.getBody();
        List<Producto> listPro = Arrays.asList(arrPro);
        List<Producto> productoBusqueda = new ArrayList<Producto>();

        if(listPro!=null){
            for(Producto producto : listPro){
                if(producto.getNombreProducto().toLowerCase().contains(str.toLowerCase())){
                    productoBusqueda.add(producto);
                }
            }
            model.addAttribute("productosLista", productoBusqueda);
        }

        return "comprador-ResultadoBusqueda";
    }
    
    @PostMapping(value="/")
    public String Retornarpag(Model model, HttpServletRequest req, String strBusqueda){
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        
        
        model.addAttribute("userid", userid);

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        str = strBusqueda;
        
        RestTemplate rest = new RestTemplate();
        String link = "http://coronafast.herokuapp.com/revisarProductos/productos/mostrar";
        ResponseEntity<Producto[]> listaProductos = rest.getForEntity(link, Producto[].class);
        Producto[] arrPro = listaProductos.getBody();
        List<Producto> listPro = Arrays.asList(arrPro);
        List<Producto> productoBusqueda = new ArrayList<Producto>();

        if(listPro!=null){
            for(Producto producto : listPro){
                if(producto.getNombreProducto().toLowerCase().contains(strBusqueda.toLowerCase())){
                    productoBusqueda.add(producto);
                }
            }
            model.addAttribute("productosLista", productoBusqueda);
        }


        return "comprador-ResultadoBusqueda";
        
    }
    
    @PostMapping(value = "/agregarFav")
    public String agregarProductoFav(Model model, HttpServletRequest req, String idProducto){

        String idUsuario = (String) req.getSession().getAttribute("idingresado");

        RestTemplate rest = new RestTemplate();

        Producto p = new Producto();

        String url = "http://coronafast.herokuapp.com/revisarCompradores/compradores/"+idUsuario+"/agregarProducto/"+idProducto;

        rest.put(url, p, Producto.class); 

        return "redirect:/ResultadosBusqueda/";
        
    }

    @PostMapping(value = "/agregarCarrito")
    public String agregarCarrito(Model model, HttpServletRequest req, String idProducto){

        String idUsuario = (String) req.getSession().getAttribute("idingresado");

        RestTemplate rest = new RestTemplate();

        Producto p = new Producto();

        String url = "http://coronafast.herokuapp.com/revisarCompradores/compradores/"+idUsuario+"/agregarCarrito/"+idProducto;

        rest.put(url, p, Producto.class); 

        return "redirect:/ResultadosBusqueda/";
    }

    @PostMapping(value = "/tiendaFav")
    public String agregarTiendaFav(Model model, HttpServletRequest req, String idTienda){

        String idUsuario = (String) req.getSession().getAttribute("idingresado");

        RestTemplate rest = new RestTemplate();

        Tienda t  = new Tienda();

        String url = "http://coronafast.herokuapp.com/revisarCompradores/compradores/"+idUsuario+"/agregarTienda/"+idTienda;

        rest.put(url, t, Tienda.class);

        return "redirect:/ResultadosBusqueda/";
    }
}
