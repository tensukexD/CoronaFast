package edu.ulima.prueba.ControllersGenerales.ControllersVendedor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Tienda;
import edu.ulima.prueba.model.Vendedor;

@Controller
@RequestMapping("/EditarDatosVendedor")

public class EditarDatosvendedor {
    @GetMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){


        String userid = (String) req.getSession().getAttribute("idingresado");
        RestTemplate rest=new RestTemplate();
        RestTemplate rest2=new RestTemplate();
        String link="http://localhost:8080/revisarTienda/tienda/seleccionar/"+userid;
        ResponseEntity<Tienda>datostienda=rest.getForEntity(link,Tienda.class); 
        String link2="http://localhost:8080/revisarVendedores/vendedores/seleccionar/"+userid;
        ResponseEntity<Vendedor>datosvendedor=rest2.getForEntity(link2,Vendedor.class); 
        model.addAttribute("tiendap", datostienda.getBody());
        model.addAttribute("vendedorp", datosvendedor.getBody());
        return "editar-DatosVendedor";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String EditarTiendayVendedor(String razonSocial,String ruc, String nombre, String distrito,String direccion, String telefono, HttpServletRequest req){
        Tienda tiendaeditada=new Tienda();
       Vendedor vendedoreditado=new Vendedor();
       String userid = (String) req.getSession().getAttribute("idingresado");
        tiendaeditada.setNombreTienda(razonSocial);
        tiendaeditada.setRUC(ruc);
        tiendaeditada.setTelefono(telefono);
        tiendaeditada.setDireccion(direccion);
        tiendaeditada.setDistrito(distrito);


        vendedoreditado.setNombre(nombre);
        vendedoreditado.setTelefono(telefono);
        RestTemplate rest = new RestTemplate();
        RestTemplate rest2 = new RestTemplate();
        String link = "http://localhost:8080/revisarTienda/tienda/actualizar/"+userid;
        String link2 = "http://localhost:8080/revisarVendedores/vendedores/actualizar/"+userid;
        rest.put(link2, vendedoreditado, Vendedor.class);
        rest2.put(link, tiendaeditada, Tienda.class);
        return "redirect:/PaginaPrincipalVendedor/";
    }
}