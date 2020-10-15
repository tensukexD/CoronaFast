package edu.ulima.prueba.ControllersGenerales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/PaginaPrincipalVendedor")


public class PaginaPrincipalVendedor {
    @GetMapping(value="/")
    public String Retornarpag(HttpServletRequest req){
        
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("comprador")){
            return "redirect:/PaginaPrincipalComprador/";
        }

        return "tienda-PaginaPrincipalVendedor";
        

        
    }
}