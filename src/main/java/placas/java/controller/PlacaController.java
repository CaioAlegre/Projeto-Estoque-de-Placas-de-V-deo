package placas.java.controller;

import placas.java.model.Placavideo;
import placas.java.model.PlacavideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class PlacaController {

    @Autowired
    private PlacavideoService service;

    // UPLOAD DO CLOUDINARY
    @Autowired
    private UploadService uploadService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("lista", service.listarPlacas());
        model.addAttribute("placa", new Placavideo());
        return "index";
    }

    // ATUALIZANDO O MÉTODO PARA RECEBER A IMAGEM 
    @PostMapping("/placas/salvar")
    public String salvar(@ModelAttribute Placavideo placa, @RequestParam("imagemPlaca") MultipartFile arquivo) {
        try {
            // Se o usuário selecionou uma foto e ela não está vazia...
            if (!arquivo.isEmpty()) {
                // Manda pro Cloudinary e recebe o link
                String urlDaImagem = uploadService.fazerUpload(arquivo);
                // Coloca o link dentro da placa
                placa.setImagemUrl(urlDaImagem);
            }
            
            // Manda o service salvar no banco (agora com a foto!)
            service.salvar(placa); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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