package edu.ulima.prueba.ControllersGenerales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;
import edu.ulima.prueba.model.Vendedor;

import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
//estos vienen de login.java

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/registroComprador")
public class ControllerRegistroComprador {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(){
        
        return "tienda-RegistroComprador";
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String crearComprador(String nombrecomp,String distrito, String direccion, String telefono,String correo, String contrasena){
        
        RestTemplate rest = new RestTemplate();
      

        Comprador comprador = new Comprador();
        comprador.setEmail(correo);
        comprador.setContrasena(contrasena);
        comprador.setTelefono(telefono);
        comprador.setDireccion(direccion);
        comprador.setNombre(nombrecomp);
        comprador.setDistrito(distrito);
   


        String link = "http://localhost:8080/revisarCompradores/compradores/agregar/";
        rest.postForObject(link, comprador, Comprador.class);

      

        return "redirect:/";
    }
    

}