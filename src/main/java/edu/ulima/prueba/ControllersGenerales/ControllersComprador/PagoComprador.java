package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/PagoCarrito")
public class PagoComprador {
    @PostMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        

        return "Comprador-CarritoComprasTarjeta";
    }
    
}