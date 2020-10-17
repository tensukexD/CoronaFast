package edu.ulima.prueba.ControllersGenerales.ControllersVendedor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Producto;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/PaginaPrincipalVendedor")


public class PaginaPrincipalVendedor {
    @GetMapping(value="/")
    public String Retornarpag(Model model,HttpServletRequest req){
        
        RestTemplate rest = new RestTemplate();

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("comprador")){
            return "redirect:/PaginaPrincipalComprador/";
        }
        
        ResponseEntity<Producto[]> listaProductos = rest.getForEntity("http://localhost:8080/revisarProductos/productosTienda/"+userid+"/mostrar",Producto[].class);
        Producto[] arrPro = listaProductos.getBody();
        List<Producto> listPro = Arrays.asList(arrPro);

        if(listPro!=null){
            model.addAttribute("productosLista", listPro);
        }

        return "tienda-PaginaPrincipalVendedor";
        

        
    }
}