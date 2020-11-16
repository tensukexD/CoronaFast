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
        String link="http://coronafast.herokuapp.com/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>comprador=rest.getForEntity(link,Comprador.class); 
        model.addAttribute("comprador", comprador.getBody());
        return "editar-DatosComprador";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editarComprador(String nombre, String distrito,String direccion, String telefono, String categoriaPreferida, HttpServletRequest req){
        
        String userid = (String) req.getSession().getAttribute("idingresado");

        String link2 = "http://coronafast.herokuapp.com/revisarCompradores/compradores/seleccionar/"+userid;

        RestTemplate rest2 = new RestTemplate();

        Comprador compradorAntiguo = rest2.getForEntity(link2, Comprador.class).getBody();

        Comprador comprador = new Comprador();
        
        comprador.setNombre(nombre);
        comprador.setTelefono(telefono);
        comprador.setDireccion(direccion);
        comprador.setDistrito(distrito);
        comprador.setCategoriaPreferida(categoriaPreferida);
        comprador.setCarritoCompras(compradorAntiguo.getCarritoCompras());
        comprador.setListaDeseados(compradorAntiguo.getListaDeseados());
        comprador.setTiendasFavoritas(compradorAntiguo.getTiendasFavoritas());

        RestTemplate rest = new RestTemplate();
        String link = "http://coronafast.herokuapp.com/revisarCompradores/compradores/actualizar/"+userid;
        rest.put(link, comprador, Comprador.class);
        return "redirect:/PaginaPrincipalComprador/";
    }
}