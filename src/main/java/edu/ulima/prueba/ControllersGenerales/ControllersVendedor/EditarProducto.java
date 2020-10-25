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
import edu.ulima.prueba.model.Tienda;
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
        String userid = (String) req.getSession().getAttribute("idingresado");
        RestTemplate rest2=new RestTemplate();
        String link="http://localhost:8080/revisarTienda/tienda/seleccionar/"+userid;
        ResponseEntity<Tienda>datostienda=rest2.getForEntity(link,Tienda.class); 

        Producto producto = new Producto();
        producto.setNombreProducto(nombreProducto);
        producto.setCategoria(categoria);
        producto.setImagen(imagen);
        producto.setPrecio(Float.parseFloat(precio));
        producto.setCantStock(Integer.parseInt(cantStock));

        producto.setDistrito(datostienda.getBody().getDistrito());
        producto.setNombreTienda(datostienda.getBody().getNombreTienda());

        RestTemplate rest = new RestTemplate();
        String link2 = "http://localhost:8080/revisarProductos/productos/actualizar/"+id;
        rest.put(link2, producto, Producto.class);
        return "redirect:/PaginaPrincipalVendedor/";
    }
}
