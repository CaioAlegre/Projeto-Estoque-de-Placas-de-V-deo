package placas.java.controller;

import placas.java.model.Placavideo;
import placas.java.model.PlacavideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlacaController {

    @Autowired
    private PlacavideoService service;

    // A ÚNICA PÁGINA
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("placa", new Placavideo()); // Para o Insert
        model.addAttribute("lista", service.listarPlacas()); // Para o Select
        return "index";
    }

    @PostMapping("/placas/salvar")
    public String salvar(@ModelAttribute Placavideo placa) {
        service.inserirPlaca(placa);
        return "redirect:/"; // Volta para a home atualizada
    }
}