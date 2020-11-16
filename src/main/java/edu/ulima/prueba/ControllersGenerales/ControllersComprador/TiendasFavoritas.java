package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;
import edu.ulima.prueba.model.Tienda;

@Controller
@RequestMapping("/TiendasFavoritas")
public class TiendasFavoritas {
    @GetMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest=new RestTemplate();

        Comprador comprador = new Comprador();
        List<Tienda> tiendas = new ArrayList<Tienda>();

        String link="http://coronafast.herokuapp.com/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>compradorResponse=rest.getForEntity(link,Comprador.class); 
        
        comprador = compradorResponse.getBody();

        for(Long i : comprador.getTiendasFavoritas()){

            String link2="http://coronafast.herokuapp.com/revisarTienda/tienda/seleccionar/"+i;

            ResponseEntity<Tienda>tienda=rest.getForEntity(link2,Tienda.class);
             
            tiendas.add(tienda.getBody());
            
        }
        model.addAttribute("tiendas", tiendas);
        model.addAttribute("idUsuario", userid);

        return "Comprador-TiendasFavoritas";
    }

    @PostMapping(value="/quitar/{id}")
    public String quitarProducto( HttpServletRequest req,Model model, @PathVariable("id") Long idTienda){

        String userid = (String) req.getSession().getAttribute("idingresado");

        RestTemplate rest = new RestTemplate();

        String link = "http://coronafast.herokuapp.com/revisarCompradores/compradores/"+userid+"/eliminarTienda/"+idTienda;

        rest.put(link,null);

        return "redirect:/TiendasFavoritas/";
    }
    
}