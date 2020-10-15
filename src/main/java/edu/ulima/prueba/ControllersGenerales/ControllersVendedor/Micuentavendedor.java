package edu.ulima.prueba.ControllersGenerales.ControllersVendedor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Tienda;
import edu.ulima.prueba.model.Vendedor;

@Controller
@RequestMapping("/DatosVendedor")
public class Micuentavendedor {
    @GetMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){


        String userid = (String) req.getSession().getAttribute("idingresado");
        RestTemplate rest=new RestTemplate();
        RestTemplate rest2=new RestTemplate();
        String link="http://localhost:8080/revisarTienda/tienda/seleccionar/"+userid;
        ResponseEntity<Tienda>datostienda=rest.getForEntity(link,Tienda.class); 
        String link2="http://localhost:8080/revisarVendedores/vendedores/seleccionar/"+userid;
        ResponseEntity<Vendedor>datosvendedor=rest2.getForEntity(link2,Vendedor.class); 
        model.addAttribute("tiendap", datostienda.getBody());
        model.addAttribute("vendedorp", datosvendedor.getBody());
        return "ver-DatosVendedor";
    }
    
}