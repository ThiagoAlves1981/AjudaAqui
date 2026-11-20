package com.AjudaAqui.trabalhoFinalDAW.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        // Redireciona automaticamente quem entra em http://localhost:8080/ para a tela de produtos
        return "redirect:/produtos";
    }
}