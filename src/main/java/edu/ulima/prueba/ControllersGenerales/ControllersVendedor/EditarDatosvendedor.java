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
import edu.ulima.prueba.model.Producto;

@Controller
@RequestMapping("/EditarDatosVendedor")

public class EditarDatosvendedor {
    @GetMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){


        String userid = (String) req.getSession().getAttribute("idingresado");
        RestTemplate rest=new RestTemplate();
        RestTemplate rest2=new RestTemplate();
        String link="http://coronafast.herokuapp.com/revisarTienda/tienda/seleccionar/"+userid;
        ResponseEntity<Tienda>datostienda=rest.getForEntity(link,Tienda.class); 
        String link2="http://coronafast.herokuapp.com/revisarVendedores/vendedores/seleccionar/"+userid;
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
        RestTemplate rest3 = new RestTemplate();
        String link = "http://coronafast.herokuapp.com/revisarTienda/tienda/actualizar/"+userid;
        String link2 = "http://coronafast.herokuapp.com/revisarVendedores/vendedores/actualizar/"+userid;
        rest.put(link2, vendedoreditado, Vendedor.class);
        rest2.put(link, tiendaeditada, Tienda.class);
        ResponseEntity<Producto[]> listaProductos = rest.getForEntity("http://coronafast.herokuapp.com/revisarProductos/productosTienda/"+userid+"/mostrar",Producto[].class);
        Producto[] arrProduc = listaProductos.getBody();
        for (int i = 0; i < arrProduc.length; i++) {
            Producto productodistrito = new Producto();
            String link3 = "http://coronafast.herokuapp.com/revisarProductos/productos/actualizar/"+arrProduc[i].getIdProducto();
            productodistrito.setImagen(arrProduc[i].getImagen());
            productodistrito.setNombreProducto(arrProduc[i].getNombreProducto());
            productodistrito.setCategoria(arrProduc[i].getCategoria());
            productodistrito.setPrecio(arrProduc[i].getPrecio()); 
            productodistrito.setCantStock(arrProduc[i].getCantStock());
            productodistrito.setPuntuacion(arrProduc[i].getPuntuacion());
            productodistrito.setNombreTienda(razonSocial);
            productodistrito.setDistrito(distrito);
            rest3.put(link3, productodistrito, Producto.class);

        }
        return "redirect:/PaginaPrincipalVendedor/";
    }

}