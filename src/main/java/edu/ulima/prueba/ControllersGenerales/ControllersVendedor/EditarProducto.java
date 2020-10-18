package edu.ulima.prueba.ControllersGenerales.ControllersVendedor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Producto;

@Controller
@RequestMapping("/EditarProducto/{id}")
public class EditarProducto {
    @GetMapping(value="/")
    public String Retornarpag(@PathVariable("id") String id, HttpServletRequest req,Model model){

        RestTemplate rest=new RestTemplate();
        String link="http://localhost:8080/revisarProductos/productoMostrar/"+id;
        ResponseEntity<Producto> producto = rest.getForEntity(link,Producto.class); 
        model.addAttribute("producto", producto.getBody()); 
        return "Vendedor-EditarProducto";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editarProducto(@PathVariable("id") String id, String nombreProducto, String categoria, String imagen, String precio, String cantStock, HttpServletRequest req){
        
        Producto producto = new Producto();
        producto.setNombreProducto(nombreProducto);
        producto.setCategoria(categoria);
        producto.setImagen(imagen);
        producto.setPrecio(Float.parseFloat(precio));
        producto.setCantStock(Integer.parseInt(cantStock));

        RestTemplate rest = new RestTemplate();
        String link = "http://localhost:8080/revisarProductos/productos/actualizar/"+id;
        rest.put(link, producto, Producto.class);
        return "redirect:/PaginaPrincipalVendedor/";
    }
}
