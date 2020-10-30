package edu.ulima.prueba.ControllersGenerales.ControllersVendedor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import edu.ulima.prueba.model.Comprador;
import edu.ulima.prueba.model.OrdenCompra;
import edu.ulima.prueba.model.Producto;
import edu.ulima.prueba.model.Tienda;
import edu.ulima.prueba.model.Vendedor;


@Controller
@RequestMapping("/ProductosVendidos")
public class ProductosVendidos {
    @GetMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("comprador")){
            return "redirect:/PaginaPrincipalComprador/";
        }

        RestTemplate rest=new RestTemplate();

        List<OrdenCompra> ordenesCompra = new ArrayList<OrdenCompra>();

        String link="http://localhost:8080/RevisarOrdenes/ordenesVendedor/mostrar/"+userid;
        ResponseEntity<OrdenCompra[]>listaOrdenesCompra=rest.getForEntity(link,OrdenCompra[].class); 
        
        ordenesCompra = Arrays.asList(listaOrdenesCompra.getBody());

        for(OrdenCompra i : ordenesCompra){

            /*String link2="http://localhost:8080/revisarTienda/tienda/seleccionar/"+i.getIdUsuarioVendedor();

            ResponseEntity<Tienda>tienda=rest.getForEntity(link2, Tienda.class);
            
            i.setNombreTienda(tienda.getBody().getNombreTienda());*/

            RestTemplate rest2=new RestTemplate();

            String link3="http://localhost:8080/revisarCompradores/compradores/seleccionar/"+i.getIdUsuarioComprador();

            ResponseEntity<Comprador> comprador = rest2.getForEntity(link3, Comprador.class);

            i.setNombreComprador(comprador.getBody().getNombre());
            i.setTelefonoComprador(comprador.getBody().getTelefono());
            i.setDistritoComprador(comprador.getBody().getDistrito());

            String link4="http://localhost:8080/revisarProductos/productoMostrar/"+i.getIdProducto();

            ResponseEntity<Producto> producto = rest2.getForEntity(link4, Producto.class);

            i.setNombreProducto(producto.getBody().getNombreProducto());
            i.setImagen(producto.getBody().getImagen());
            
        }
        model.addAttribute("ordenesCompra", ordenesCompra);

        return "Vendedor-ProductosVendidos";
    }
    @PostMapping(value="/actualizarestado")
    public String actualizarestado( HttpServletRequest req,Model model,String estado,String idtransac){
        OrdenCompra nuevo =new OrdenCompra();
        nuevo.setEstado(estado);
        RestTemplate rest3=new RestTemplate();
        String link5="http://localhost:8080/RevisarOrdenes/ordenes/actualizarestado/"+idtransac;
        rest3.put(link5, nuevo, OrdenCompra.class);
        return "redirect:/ProductosVendidos/";
    }
}
