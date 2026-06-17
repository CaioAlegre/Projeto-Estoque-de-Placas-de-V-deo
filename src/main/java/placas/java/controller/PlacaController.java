package placas.java.controller;

import placas.java.model.Placavideo;
import placas.java.model.PlacavideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class PlacaController {

    @Autowired
    private PlacavideoService service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("lista", service.listarPlacas());
        model.addAttribute("placa", new Placavideo());
        return "index";
    }

    @PostMapping("/placas/salvar")
    public String salvar(@ModelAttribute Placavideo placa) {
        service.salvar(placa);
        return "redirect:/";
    }

    @GetMapping("/placas/editar/{id}")
    public String editar(@PathVariable("id") UUID id, Model model) {
        Placavideo placa = service.buscarPorId(id);
        model.addAttribute("placa", placa);
        model.addAttribute("lista", service.listarPlacas());
        return "index"; // Reutiliza a mesma tela, preenchendo o formulário
    }

    @PostMapping("/placas/deletar/{id}")
    public String deletar(@PathVariable("id") UUID id) {
        service.deletar(id);
        return "redirect:/";
    }
}