package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;

@Controller
@RequestMapping("/EditarDatosComprador")

public class EditarDatoscomprador {
    @GetMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){

        String userid = (String) req.getSession().getAttribute("idingresado");
        RestTemplate rest=new RestTemplate();
        String link="http://localhost:8080/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>comprador=rest.getForEntity(link,Comprador.class); 
        model.addAttribute("comprador", comprador.getBody());
        return "editar-DatosComprador";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editarComprador(String nombre, String distrito,String direccion, String telefono, String categoriaPreferida, HttpServletRequest req){
        
        Comprador comprador = new Comprador();
        String userid = (String) req.getSession().getAttribute("idingresado");
        comprador.setNombre(nombre);
        comprador.setTelefono(telefono);
        comprador.setDireccion(direccion);
        comprador.setDistrito(distrito);
        comprador.setCategoriaPreferida(categoriaPreferida);

        RestTemplate rest = new RestTemplate();
        String link = "http://localhost:8080/revisarCompradores/compradores/actualizar/"+userid;
        rest.put(link, comprador, Comprador.class);
        return "redirect:/PaginaPrincipalComprador/";
    }
}