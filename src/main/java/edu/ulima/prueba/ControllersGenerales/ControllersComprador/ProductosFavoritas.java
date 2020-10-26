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
import edu.ulima.prueba.model.Producto;


@Controller
@RequestMapping("/ProductosFavoritos")
public class ProductosFavoritas {
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
        List<Producto> productos = new ArrayList<Producto>();

        String link="http://localhost:8080/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>compradorResponse=rest.getForEntity(link,Comprador.class); 
        
        comprador = compradorResponse.getBody();

        for(Long i : comprador.getListaDeseados()){

            String link2="http://localhost:8080/revisarProductos/productoMostrar/"+i;

            ResponseEntity<Producto>producto=rest.getForEntity(link2, Producto.class);
             
            productos.add(producto.getBody());
            
        }
        model.addAttribute("productos", productos);
        model.addAttribute("idUsuario", userid);

        return "Comprador-ProductosFavoritos";
    }

    @PostMapping(value="/quitar/{id}")
    public String quitarProducto( HttpServletRequest req,Model model, @PathVariable("id") Long idProducto){

        String userid = (String) req.getSession().getAttribute("idingresado");

        RestTemplate rest = new RestTemplate();

        String link = "http://localhost:8080/revisarCompradores/compradores/"+userid+"/eliminarProducto/"+idProducto;

        rest.put(link,null);

        return "redirect:/ProductosFavoritos/";
    }
    
}
