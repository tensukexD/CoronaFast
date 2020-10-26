package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/VisualizarTienda")
public class ControllerVisualizarTienda {
    @GetMapping(value="/")
    public String Retornarpag(HttpServletRequest req, Model model){
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }
        RestTemplate rest=new RestTemplate();



        
        return "???";
}
}