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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;
import edu.ulima.prueba.model.Producto;

@Controller
@RequestMapping("/CarritoCompras")
public class CarritoCompras {
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

        String link="http://coronafast.herokuapp.com/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>compradorResponse=rest.getForEntity(link,Comprador.class); 
        
        comprador = compradorResponse.getBody();

        for(Long i : comprador.getCarritoCompras()){

            String link2="http://coronafast.herokuapp.com/revisarProductos/productoMostrar/"+i;

            ResponseEntity<Producto>producto=rest.getForEntity(link2,Producto.class);
             
            productos.add(producto.getBody());
            
        }
        model.addAttribute("productos", productos);
        model.addAttribute("idUsuario", userid);

        return "ver-CarritoCompras";
    }

    @PostMapping(value="/quitar/")
    public String quitarProducto( HttpServletRequest req,Model model, Long idProducto){

        String userid = (String) req.getSession().getAttribute("idingresado");

        System.out.println(idProducto);
        System.out.println(idProducto);
        System.out.println(idProducto);
        System.out.println(idProducto);
        System.out.println(idProducto);
        System.out.println(idProducto);
        System.out.println(idProducto);
        System.out.println(idProducto);

        RestTemplate rest = new RestTemplate();

        String link = "http://coronafast.herokuapp.com/revisarCompradores/compradores/"+userid+"/eliminarCarrito/"+idProducto;

        rest.put(link,null);

        return "redirect:/CarritoCompras/";
    }
    @PostMapping(value="/comprar")
    public String comprarcarrito(@RequestParam("quantity[]") List<String> quantity,String inputotal, HttpServletRequest req,Model model ){
        String[] array = new String[quantity.size()];
         array=quantity.toArray(array);
        req.getSession().setAttribute("cantidadproductosCompra", array);
        req.getSession().setAttribute("preciototalCompra", inputotal);
        String[] prueba1=(String[])req.getSession().getAttribute("cantidadproductos");
        
        for (String i : quantity){
            if(i.equalsIgnoreCase("0")){
                return "redirect:/errorStock/";
            }
        }
        return "redirect:/PagodePedido/";
    }
    
}