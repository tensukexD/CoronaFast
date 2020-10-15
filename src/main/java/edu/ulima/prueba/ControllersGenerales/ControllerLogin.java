package edu.ulima.prueba.ControllersGenerales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.client.RestTemplate;

import edu.ulima.prueba.model.Comprador;
import edu.ulima.prueba.model.Vendedor;

import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
//estos vienen de login.java

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class ControllerLogin {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(HttpServletRequest req){
        String userid = (String) req.getSession().getAttribute("idingresado");
        String tipo = (String) req.getSession().getAttribute("tipo");

        if(userid == null){
            return "tienda-Loguearse";
        }else{
            if(tipo == "comprador"){
                return "redirect:/PaginaPrincipalComprador/";
            }else{
                return "redirect:/PaginaPrincipalVendedor/";
            }
        }

        
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String Iniciar(String usuarioaenviar,String Contraenviar ,HttpServletRequest req){
       
        

        System.out.println("nombre ingresado: " +usuarioaenviar);
        System.out.println("contrasenia ingresada: " + Contraenviar);
       
        //req.getSession().setAttribute("usuario", f.getCampo1());

        RestTemplate rest=new RestTemplate();

        ResponseEntity<Comprador[]>listadeusuariosCompradores=rest.getForEntity("http://localhost:8080/revisarCompradores/compradores/mostrar",Comprador[].class);
        Comprador[] lus1=listadeusuariosCompradores.getBody();
        List<Comprador> lus2=Arrays.asList(lus1); 

        ResponseEntity<Vendedor[]>listadeusuariosVendedores=rest.getForEntity("http://localhost:8080/revisarVendedores/vendedores/mostrar",Vendedor[].class);
        Vendedor[] luss1=listadeusuariosVendedores.getBody();
        List<Vendedor> luss2=Arrays.asList(luss1); 

        for(Comprador usr : lus2){
            //System.out.println(usr);
            System.out.println("**********************************");
            System.out.println("COINCIDENCIA DE CORREO ENCONTRADA");
            System.out.println("USUARIO CON ID: "+usr.getIdUsuario());
            System.out.println("con correo: "+usr.getEmail());
            System.out.println("con contrasenia: "+usr.getContrasena());
            
            System.out.println("**********************************");
            System.out.println("");
            
            
            
            if(usr.getEmail().equals(usuarioaenviar)){
                if(usr.getContrasena().equals(Contraenviar)){
                    System.out.println("**********************************NICE **********************************");
                    req.getSession().setAttribute("idingresado", Long.toString(usr.getIdUsuario()));
                    req.getSession().setAttribute("tipo", "comprador");
                    return "redirect:/PaginaPrincipalComprador/";
                }
            }
        }
        for(Vendedor usr : luss2){
            //System.out.println(usr);
            System.out.println("**********************************");
            System.out.println("COINCIDENCIA DE CORREO ENCONTRADA");
            System.out.println("USUARIO CON ID: "+usr.getIdUsuario());
            System.out.println("con correo: "+usr.getEmail());
            System.out.println("con contrasenia: "+usr.getContrasena());
            
            System.out.println("**********************************");
            System.out.println("VENDEDOR");
            
            
            
            if(usr.getEmail().equals(usuarioaenviar)){
                System.out.println("ENTRASTE?");
                if(usr.getContrasena().equals(Contraenviar)){
                    System.out.println("**********************************NICE **********************************");
                    req.getSession().setAttribute("idingresado", Long.toString(usr.getIdUsuario()));
                    req.getSession().setAttribute("tipo", "vendedor");
                    return "redirect:/PaginaPrincipalVendedor/";
                }
            }
        }

        return "redirect:/";
    }
    

}