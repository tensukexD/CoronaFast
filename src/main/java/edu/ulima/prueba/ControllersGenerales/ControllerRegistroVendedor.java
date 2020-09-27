package edu.ulima.prueba.ControllersGenerales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Tienda;
import edu.ulima.prueba.model.Vendedor;

@Controller
@RequestMapping("/registroVendedor")
public class ControllerRegistroVendedor {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(){
        
        return "tienda-RegistroVendedor";
    }
    
    @PostMapping(value = "/crear")
    public String crearVendedor(String email, String contrasena, String telefono, String direccion, String ruc, String razonSocial){
        
        RestTemplate rest = new RestTemplate();
        RestTemplate rest2 = new RestTemplate();

        Vendedor vendedor = new Vendedor();
        vendedor.setEmail(email);
        vendedor.setContrasena(contrasena);
        vendedor.setTelefono(telefono);

        Tienda tienda = new Tienda();
        tienda.setIdUsuario(Long.parseLong("1")); //harcodeado 4 the lulz
        tienda.setRUC(ruc);
        tienda.setNombreTienda(razonSocial);
        tienda.setDistrito("");
        tienda.setDireccion(direccion);
        tienda.setTelefono(telefono);


        String link = "http://localhost:8080/revisarVendedores/vendedores/agregar/";
        rest.postForObject(link, vendedor, Vendedor.class);

        String link2 = "http://localhost:8080/revisarTienda/1/tiendas/agregar/";
        rest2.postForObject(link2, tienda, Tienda.class);

        return "redirect:/";
    }
}