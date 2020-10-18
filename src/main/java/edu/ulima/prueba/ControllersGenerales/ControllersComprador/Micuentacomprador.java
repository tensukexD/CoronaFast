package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;

@Controller
@RequestMapping("/DatosComprador")
public class Micuentacomprador {
    @GetMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){


        String userid = (String) req.getSession().getAttribute("idingresado");
        RestTemplate rest=new RestTemplate();
        String link="http://localhost:8080/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>comprador=rest.getForEntity(link,Comprador.class); 
        model.addAttribute("comprador", comprador.getBody());
        return "ver-DatosComprador";
    }
    
}