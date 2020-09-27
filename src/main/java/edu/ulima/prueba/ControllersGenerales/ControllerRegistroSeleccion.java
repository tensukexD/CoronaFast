package edu.ulima.prueba.ControllersGenerales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/registroSeleccion")
public class ControllerRegistroSeleccion {

    @GetMapping(value="/")
    public String Retornarpag(HttpServletRequest req){
        

        return "tienda-RegistroEscoger";
    }

    @GetMapping(value="/vendedor")
    public String vendedor(){
        return "redirect:/registroVendedor/";
    }
    
    @GetMapping(value="/comprador")
    public String comprador(){
        return "redirect:/registroComprador/";
    }
    
}