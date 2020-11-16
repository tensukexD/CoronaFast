package edu.ulima.prueba.ControllersGenerales;

import java.io.Console;

import javax.servlet.http.HttpServletRequest;

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
    public String getIndex(HttpServletRequest req){
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "tienda-RegistroVendedor";
        }else{
            if(tipo == "comprador"){
                return "redirect:/PaginaPrincipalComprador/";
            }else{
                return "redirect:/PaginaPrincipalVendedor/";
            }
        }
        
    }
    
    @PostMapping(value = "/crear")
    public String crearVendedor(String nombre, String email, String contrasena, String telefono, String direccion, String ruc, String razonSocial, String distrito){
        
        RestTemplate rest = new RestTemplate();
        RestTemplate rest2 = new RestTemplate();

        Vendedor vendedor = new Vendedor();
        vendedor.setNombre(nombre);
        vendedor.setEmail(email);
        vendedor.setContrasena(contrasena);
        vendedor.setTelefono(telefono);

        String link = "http://coronafast.herokuapp.com/revisarVendedores/vendedores/agregar/";
        rest.postForObject(link, vendedor, Vendedor.class);

        String link3 = "http://coronafast.herokuapp.com/revisarVendedores/vendedores/seleccionarEmail/"+email+"/";

        Vendedor vendedorNuevo = rest.getForObject(link3, Vendedor.class);

        if(vendedorNuevo==null){
            return "redirect:/registroVendedor/crear";
        }else{
            Tienda tienda = new Tienda();
            tienda.setIdUsuario(vendedorNuevo.getIdUsuario()); 
            tienda.setRUC(ruc);
            tienda.setNombreTienda(razonSocial);
            tienda.setDireccion(direccion);
            tienda.setTelefono(telefono);
            tienda.setDistrito(distrito);  

            String link2 = "http://coronafast.herokuapp.com/revisarTienda/tiendas/agregar/";
            rest2.postForObject(link2, tienda, Tienda.class);
        }
        

        return "redirect:/";
    }
}