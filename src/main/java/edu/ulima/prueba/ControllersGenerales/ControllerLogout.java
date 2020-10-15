package edu.ulima.prueba.ControllersGenerales;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class ControllerLogout {

    @GetMapping(value="/")
    public String Retornarpag(HttpServletRequest req){
        req.getSession().invalidate();
        return "redirect:/";
    }
}
