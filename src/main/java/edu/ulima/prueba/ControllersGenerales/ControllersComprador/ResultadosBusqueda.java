package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Producto;

@Controller
@RequestMapping("/ResultadosBusqueda")
public class ResultadosBusqueda {
    @PostMapping(value="/")
    public String Retornarpag(Model model, HttpServletRequest req, String strBusqueda){
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        model.addAttribute("userid", userid);

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }

        RestTemplate rest = new RestTemplate();
        String link = "http://localhost:8080/revisarProductos/productos/mostrar";
        ResponseEntity<Producto[]> listaProductos = rest.getForEntity(link, Producto[].class);
        Producto[] arrPro = listaProductos.getBody();
        List<Producto> listPro = Arrays.asList(arrPro);
        List<Producto> productoBusqueda = new ArrayList<Producto>();

        if(listPro!=null){
            for(Producto producto : listPro){
                if(producto.getNombreProducto().toLowerCase().contains(strBusqueda.toLowerCase())){
                    productoBusqueda.add(producto);
                }
            }
            model.addAttribute("productosLista", productoBusqueda);
        }


        return "comprador-ResultadoBusqueda";
        
    }
}
