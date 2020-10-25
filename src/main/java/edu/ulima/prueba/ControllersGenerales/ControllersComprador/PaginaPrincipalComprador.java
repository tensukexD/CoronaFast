package edu.ulima.prueba.ControllersGenerales.ControllersComprador;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;
import edu.ulima.prueba.model.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/PaginaPrincipalComprador")
public class PaginaPrincipalComprador {
    @GetMapping(value="/")
    public String Retornarpag(HttpServletRequest req, Model model){
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "redirect:/";
        }
        if(tipo.equals("vendedor")){
            return "redirect:/PaginaPrincipalVendedor/";
        }
        RestTemplate rest=new RestTemplate();
        
        
        String link="http://localhost:8080/revisarCompradores/compradores/seleccionar/"+userid;
        ResponseEntity<Comprador>comprador=rest.getForEntity(link,Comprador.class); 
        
        RestTemplate rest2=new RestTemplate();
        String link2="http://localhost:8080/revisarProductos/producto/seleccionar/"+comprador.getBody().getCategoriaPreferida();
        ResponseEntity<Producto[]>productoscategoricos=rest2.getForEntity(link2,Producto[].class); 
        int numero=0;
        Producto[] productoscategoricosr=productoscategoricos.getBody();
        Producto[] productoscategoricosfinal=new Producto[8];
        ArrayList numbers = new ArrayList();
                for(int u=0; u < productoscategoricosr.length; u++)
                {
                numbers.add(u);
                }
                Collections.shuffle(numbers);
        
        if (productoscategoricosr.length>8){
            for(int i=0;i<8;i++){
                
               
             numero=(Integer)numbers.get(i);
                productoscategoricosfinal[i]=productoscategoricosr[numero];  
                     }
                Producto[] productoscategoricosfinal1=new Producto[4];
               Producto[] productoscategoricosfinal2=new Producto[4];
               for(int i=0;i<8;i++){
           
                if(i<=3){
                    productoscategoricosfinal1[i]=productoscategoricosfinal[i];
                }
                else{
                    productoscategoricosfinal2[i-4]=productoscategoricosfinal[i];
    
                }
               }
                 List<Producto> listacategorica1=Arrays.asList(productoscategoricosfinal1);  
                 List<Producto> listacategorica2=Arrays.asList(productoscategoricosfinal2);     
                model.addAttribute("listoncategorico1", listacategorica1);
                model.addAttribute("listoncategorico2", listacategorica2);
            }
       else{
            if(productoscategoricosr.length<=4){
                Producto[] productoscategoricosfinal1=new Producto[productoscategoricosr.length];
                Producto[] productoscategoricosfinal2=new Producto[0];
                for(int i=0;i<productoscategoricosr.length;i++){
                    productoscategoricosfinal1[i]=productoscategoricosr[i];
                }
                List<Producto> listacategorica1=Arrays.asList(productoscategoricosfinal1); 
                List<Producto> listacategorica2=Arrays.asList(productoscategoricosfinal2);
                model.addAttribute("listoncategorico1", listacategorica1);
                model.addAttribute("listoncategorico2", listacategorica2); 

            }
            else{
                Producto[] productoscategoricosfinal1=new Producto[4];
                Producto[] productoscategoricosfinal2=new Producto[productoscategoricosr.length-4];
                for(int i=0;i<productoscategoricosr.length;i++){
           
                    if(i<=3){
                        productoscategoricosfinal1[i]=productoscategoricosr[i];
                    }
                    else{
                        productoscategoricosfinal2[i-4]=productoscategoricosr[i];
        
                    }
                   }
                   List<Producto> listacategorica1=Arrays.asList(productoscategoricosfinal1);  
                 List<Producto> listacategorica2=Arrays.asList(productoscategoricosfinal2); 
                   model.addAttribute("listoncategorico1", listacategorica1);
                   model.addAttribute("listoncategorico2", listacategorica2);
            }  
       }
       RestTemplate rest3=new RestTemplate();
        //String link3="http://localhost:8080/revisarProductos/producto/seleccionar/"+comprador.getDistrito();
        String link3="http://localhost:8080/revisarProductos/producto/seleccionar/"+comprador.getBody().getCategoriaPreferida();
       ResponseEntity<Producto[]>productosubicacion=rest3.getForEntity(link2,Producto[].class); 
        Producto[] productosubicacionr=productosubicacion.getBody();
        Producto[] productosubicacionfinal=new Producto[8];
        ArrayList numbers2 = new ArrayList();
        int numero2=0;
                for(int u=0; u < productosubicacionr.length; u++)
                {
                numbers2.add(u);
                }
                Collections.shuffle(numbers2);
        
        if (productosubicacionr.length>8){
            for(int i=0;i<8;i++){
                
               
             numero2=(Integer)numbers2.get(i);
             productosubicacionfinal[i]=productosubicacionr[numero];  
                     }
                Producto[] productosubicacionalesfinal1=new Producto[4];
               Producto[] productosubicacionalesfinal2=new Producto[4];
               for(int i=0;i<8;i++){
           
                if(i<=3){
                    productosubicacionalesfinal1[i]=productosubicacionfinal[i];
                }
                else{
                    productosubicacionalesfinal2[i-4]=productosubicacionfinal[i];
    
                }
               }
                 List<Producto> listaubicacion1=Arrays.asList(productosubicacionalesfinal1);  
                 List<Producto> listaubicacion2=Arrays.asList(productosubicacionalesfinal2);     
                model.addAttribute("listonubicacion1", listaubicacion1);
                model.addAttribute("listonubicacion2", listaubicacion2);
            }
       else{
            if(productosubicacionr.length<=4){
                Producto[] productosubicacionalesfinal1=new Producto[productosubicacionr.length];
                Producto[] productosubicacionalesfinal2=new Producto[0];
                for(int i=0;i<productosubicacionr.length;i++){
                    productosubicacionalesfinal1[i]=productosubicacionr[i];
                }
                List<Producto> listaubicacion1=Arrays.asList(productosubicacionalesfinal1); 
                List<Producto> listaubicacion2=Arrays.asList(productosubicacionalesfinal2);
                model.addAttribute("listonubicacion1", listaubicacion1);
                model.addAttribute("listonubicacion2", listaubicacion2); 

            }
            else{
                Producto[] productosubicacionalesfinal1=new Producto[4];
                Producto[] productosubicacionalesfinal2=new Producto[productosubicacionr.length-4];
                for(int i=0;i<productosubicacionr.length;i++){
           
                    if(i<=3){
                        productosubicacionalesfinal1[i]=productosubicacionr[i];
                    }
                    else{
                        productosubicacionalesfinal2[i-4]=productosubicacionr[i];
        
                    }
                   }
                   List<Producto> listaubicacion1=Arrays.asList(productosubicacionalesfinal1);  
                 List<Producto> listaubicacion2=Arrays.asList(productosubicacionalesfinal2); 
                   model.addAttribute("listonubicacion1", listaubicacion1);
                   model.addAttribute("listonubicacion2", listaubicacion2);
            }  
       }
         
        
        return "tienda-PaginaPrincipalComprador";
        
    }
    
}