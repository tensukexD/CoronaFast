package edu.ulima.prueba.ControllersGenerales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/PaginaPrincipalComprador")
public class PaginaPrincipalComprador {
    @GetMapping(value="/")
    public String Retornarpag(HttpServletRequest req){
        

        return "tienda-PaginaPrincipalComprador";
    }
    
}