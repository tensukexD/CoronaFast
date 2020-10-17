package edu.ulima.prueba.ControllersGenerales;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Producto;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/PaginaPrincipalVendedor")


public class PaginaPrincipalVendedor {
    @GetMapping(value="/")
    public String Retornarpag(Model model,HttpServletRequest req){
        
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("comprador")){
            return "redirect:/PaginaPrincipalComprador/";
        }
        RestTemplate rest=new RestTemplate();
        ResponseEntity<Producto[]>listadeusuarios=rest.getForEntity("https://proyectowebfinal1.herokuapp.com/revisarUsuarios/usuarios/mostrar/Inactivo",Producto[].class);  
            Producto[] lus1=listadeusuarios.getBody();
            List<Producto> lus2=Arrays.asList(lus1);
            
            if (lus2 != null) {
                model.addAttribute("liston", lus2);
                model.addAttribute("cantidad", lus2.size());
    
                
            }















        return "tienda-PaginaPrincipalVendedor";
        

        
    }
}