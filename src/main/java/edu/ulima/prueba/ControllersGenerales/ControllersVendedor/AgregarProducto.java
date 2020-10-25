package edu.ulima.prueba.ControllersGenerales.ControllersVendedor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Producto;
import edu.ulima.prueba.model.Tienda;
@Controller
@RequestMapping("/AgregarProducto")
public class AgregarProducto {

    @GetMapping(value="/")
    public String retornarPag(Model model,HttpServletRequest req){
        
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("comprador")){
            return "redirect:/PaginaPrincipalComprador/";
        }
        
        return "Vendedor-AñadirProducto";
        
    }
    
    @PostMapping(value="/crear")
    public String crearProducto(Model model,HttpServletRequest req, String nombreProducto, String url, String categoria, String precio, String stock){

        Producto producto = new Producto();
        String userid = (String) req.getSession().getAttribute("idingresado");
        RestTemplate rest = new RestTemplate();
        String link2 ="http://localhost:8080/revisarTienda/tienda/seleccionar/"+userid;
        Tienda tienda =rest.getForObject(link2, Tienda.class);
        
        producto.setIdUsuario(Long.parseLong(userid));
        producto.setNombreProducto(nombreProducto);
        producto.setImagen(url);
        producto.setCategoria(categoria);
        producto.setPrecio(Float.parseFloat(precio));
        producto.setCantStock(Integer.parseInt(stock));
        producto.setDistrito(tienda.getDistrito());
        producto.setNombreTienda(tienda.getNombreTienda());

        String link = "http://localhost:8080/revisarProductos/productos/agregar";

        rest.postForObject(link, producto, Producto.class);

        return "redirect:/PaginaPrincipalVendedor/";
    }
}
