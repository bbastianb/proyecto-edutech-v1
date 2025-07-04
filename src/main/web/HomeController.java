package com.proyecto.Edutech_v1.proyecto.Edutech_v1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // redirige al Swagger UI
        return "redirect:/swagger-ui.html";
    }

}
