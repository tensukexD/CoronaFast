package edu.ulima.prueba.ControllersGenerales.ControllersVendedor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import edu.ulima.prueba.model.OrdenCompra;
import edu.ulima.prueba.model.Producto;
import edu.ulima.prueba.model.Tienda;


@Controller 
@RequestMapping("/Reportedeventas")
public class Reportedeventas {
    String filtrote="neutral";
    String desde;
    String hasta;
    LocalDate fechaHasta;
    LocalDate fechaDesde;
    @GetMapping(value="/")
    public String Retornarpag(Model model,HttpServletRequest req){
        float total=0;
        int cantidad=0;
        RestTemplate rest = new RestTemplate();

        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("comprador")){
            return "redirect:/PaginaPrincipalComprador/";
        }
        List<OrdenCompra> ordenesCompra = new ArrayList<OrdenCompra>();
        List<OrdenCompra> ordenesCompraActualizado = new ArrayList<OrdenCompra>();
        RestTemplate rest2=new RestTemplate();
        ResponseEntity<OrdenCompra[]>listadeordenes=rest2.getForEntity("https://coronafast.herokuapp.com/RevisarOrdenes/ordenesVendedor/mostrar/"+userid,OrdenCompra[].class);
        ordenesCompra = Arrays.asList(listadeordenes.getBody()); 

        
        
        if(filtrote.equals("neutral")){
            for(OrdenCompra i : ordenesCompra){
                    total=i.getTotal()+total;
                    cantidad=i.getCantidad()+cantidad;
                }
            
                model.addAttribute("ordenesCompra", ordenesCompra);
                model.addAttribute("total", total);
                model.addAttribute("cantidadtotal", cantidad);
            }
        else {
            for(OrdenCompra i : ordenesCompra){
                //hasta = 11/11/2020 desde = 12/12/2020

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fechaOrden = LocalDate.parse(i.getFechaCompra(), formatter);  
                if(fechaOrden.isAfter(fechaDesde) && fechaOrden.isBefore(fechaHasta)){
                    total=i.getTotal()+total;
                    cantidad=i.getCantidad()+cantidad;
                    ordenesCompraActualizado.add(i);
                }
                


             
            }
            filtrote="neutral";
            model.addAttribute("ordenesCompra", ordenesCompraActualizado);
            model.addAttribute("total", total);
            model.addAttribute("cantidadtotal", cantidad);
        }
        return "Vendedor-ReporteVentas";

    }

    @RequestMapping(value="/filtrar" , method=RequestMethod.POST)
        public String processForm(String  desdefecha, String  hastafecha) {
            System.out.println(desdefecha);
            System.out.println(desdefecha);
            System.out.println(desdefecha);
            System.out.println(desdefecha);
            System.out.println(desdefecha);
            System.out.println(hastafecha);
            System.out.println(hastafecha);
            System.out.println(hastafecha);
            System.out.println(hastafecha);
            System.out.println(hastafecha);
            System.out.println(hastafecha);

            hasta=hastafecha;
            desde=desdefecha;
            filtrote="noesneutral";

            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            fechaHasta = LocalDate.parse(hasta, formatter);  
            fechaDesde = LocalDate.parse(desde, formatter);
            System.out.println("llegoaca? no creo");
            System.out.println("llegoaca? no creo");
            System.out.println("llegoaca? no creo");
            System.out.println("llegoaca? no creo");
            System.out.println(fechaHasta);
            return "redirect:/Reportedeventas/";
    }
}





