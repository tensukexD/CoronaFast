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
import org.springframework.web.bind.annotation.PostMapping;
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

    
}