package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;
import edu.ulima.prueba.model.OrdenCompra;
import edu.ulima.prueba.model.Producto;

@Controller
@RequestMapping("/PagodePedido")
public class Compra {
    @GetMapping(value="/")
    public String Retornarpag( HttpServletRequest req,Model model){
        String[] prueba1=(String[])req.getSession().getAttribute("cantidadproductosCompra");
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }
        Comprador comprador = new Comprador();
        RestTemplate rest=new RestTemplate();
        String link="http://localhost:8080/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>compradorResponse=rest.getForEntity(link,Comprador.class); 
        comprador = compradorResponse.getBody();
       if(comprador.getCarritoCompras()==null || prueba1.equals(null)){

           return "redirect:/CarritoCompras/";
        }
        String total=(String)req.getSession().getAttribute("preciototalCompra");
        model.addAttribute("precioTotal", total);
        float subtotal=Float.parseFloat(total)-20;
        String subtotalfinal=Float.toString(subtotal);
        model.addAttribute("precioSubTotal", subtotalfinal);
        model.addAttribute("precioEnvio", "20");
        

        return "Comprador-CarritoComprasTarjeta";
        
}
@PostMapping(value="/comprar")
    public String comprarcarrito(String direccionexacta,String notasadicionales,HttpServletRequest req){
        String userid = (String) req.getSession().getAttribute("idingresado");
        Comprador comprador = new Comprador();
        RestTemplate rest=new RestTemplate();
        String[] prueba1=(String[])req.getSession().getAttribute("cantidadproductosCompra");
        String link="http://localhost:8080/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>compradorResponse=rest.getForEntity(link,Comprador.class); 
        comprador = compradorResponse.getBody();
        ArrayList<Long> carrito=comprador.getCarritoCompras();
                 System.out.println(prueba1);
                System.out.println(prueba1);
                System.out.println(prueba1);
                System.out.println(prueba1);
                System.out.println(prueba1[0]);
                System.out.println(prueba1[0]);
                System.out.println(prueba1[0]);
                System.out.println(Long.toString(carrito.get(0)));
        int comprobante=0;
        int auxiliar=0;
        for(Long i : comprador.getCarritoCompras()){
            RestTemplate rest10=new RestTemplate();
            String linkcompro1="http://localhost:8080/revisarProductos/productoMostrar/"+i;
            System.out.println(linkcompro1);
            System.out.println(linkcompro1);
            ResponseEntity<Producto>productofakec=rest10.getForEntity(linkcompro1,Producto.class);
            Producto productoc=productofakec.getBody();
            if(productoc.getCantStock()>=Integer.parseInt(prueba1[auxiliar])){
                System.out.println("LLEGO ACA");
                System.out.println("LLEGO ACA");
                System.out.println("LLEGO ACA");
                System.out.println("LLEGO ACA");
                System.out.println("LLEGO ACA");
                System.out.println("LLEGO ACA");
                System.out.println("LLEGO ACA");
                comprobante=comprobante+1;
            }
            auxiliar=auxiliar+1;

        }

        if(comprobante==prueba1.length){

        for(int i=0;i<prueba1.length;i++){
            RestTemplate rest20=new RestTemplate();
            System.out.println("LLEGO ACA2");
            System.out.println("LLEGO ACA2");
            System.out.println("LLEGO ACA2");
            System.out.println("LLEGO ACA2");
            System.out.println("LLEGO ACA2");
            System.out.println("LLEGO ACA2");
            System.out.println("LLEGO ACA2");
            String link2="http://localhost:8080/revisarProductos/productoMostrar/"+Long.toString(carrito.get(i));

            ResponseEntity<Producto>productofake=rest20.getForEntity(link2,Producto.class);
            Producto producto=productofake.getBody();
            int cantidad=Integer.parseInt(prueba1[i]);
            float precioProducto=producto.getPrecio()*cantidad; 
            Long idcomprador=Long.valueOf(userid);
            Long idvendedor=Long.valueOf(producto.getIdUsuario());
            Long idproducto=carrito.get(i);
            String estado="Pendiente";
            ///ACTUALIZAR STOCK////
                Producto nuevoproducto=new Producto();
                RestTemplate rest3=new RestTemplate();
                String link3="http://localhost:8080/revisarProductos/productos/actualizar/"+Long.toString(carrito.get(i));
                nuevoproducto.setIdUsuario(producto.getIdUsuario());
                nuevoproducto.setImagen(producto.getImagen());
                nuevoproducto.setNombreProducto(producto.getNombreProducto());
                nuevoproducto.setCategoria(producto.getCategoria());
                nuevoproducto.setPrecio(producto.getPrecio());
                nuevoproducto.setCantStock(producto.getCantStock()-cantidad);
                nuevoproducto.setDistrito(producto.getDistrito());
                nuevoproducto.setNombreTienda(producto.getNombreTienda());
                rest3.put(link3, nuevoproducto, Producto.class);
            
            ///CREACION ORDEN DE Compra//
            OrdenCompra orden=new OrdenCompra();
            RestTemplate rest4=new RestTemplate();
            orden.setIdUsuarioVendedor(idvendedor);
            orden.setIdUsuarioComprador(idcomprador);
            orden.setDireccionexacta(direccionexacta);
            orden.setIdProducto(carrito.get(i));
            orden.setNota(notasadicionales);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
             String fecha =sdf.format(new Date());
            orden.setFechaCompra(fecha);
            orden.setCantidad(cantidad);
            orden.setEstado(estado);
            orden.setTotal(precioProducto);
            String link4="http://localhost:8080/RevisarOrdenes/ordenes/agregar/";
            rest4.postForObject(link4, orden, OrdenCompra.class);
            
        }

       comprador.setCarritoCompras(null);
       req.getSession().setAttribute("cantidadproductosCompra", null);
       req.getSession().setAttribute("preciototalCompra", null);
        RestTemplate rest5=new RestTemplate();
       String link5= "http://localhost:8080/revisarCompradores/compradores/actualizar/"+userid;
       rest5.put(link5, comprador, Comprador.class);
        return "redirect:/CarritoCompras/";
    }
    return "redirect:/CarritoCompras/";
    }


}