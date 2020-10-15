package edu.ulima.prueba.ControllersGenerales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;


//estos vienen de login.java
@Controller
@RequestMapping("/registroComprador")
public class ControllerRegistroComprador {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(HttpServletRequest req){
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "tienda-RegistroComprador";
        }else{
            if(tipo == "comprador"){
                return "redirect:/PaginaPrincipalComprador/";
            }else{
                return "redirect:/PaginaPrincipalVendedor/";
            }
        }
        
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