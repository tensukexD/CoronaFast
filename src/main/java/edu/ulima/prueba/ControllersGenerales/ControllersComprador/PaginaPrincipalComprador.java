package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/PaginaPrincipalComprador")
public class PaginaPrincipalComprador {
    @GetMapping(value="/")
    public String Retornarpag(HttpServletRequest req){
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        return "tienda-PaginaPrincipalComprador";
        
    }
    
}