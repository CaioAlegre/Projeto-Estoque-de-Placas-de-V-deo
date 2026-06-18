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
        
        // 1. Tenta fazer o upload da foto isoladamente
        if (arquivo != null && !arquivo.isEmpty()) {
            try {
                String urlDaImagem = uploadService.fazerUpload(arquivo);
                placa.setImagemUrl(urlDaImagem);
            } catch (Exception e) {
                // Se a foto der erro, avisa no terminal, mas NÃO para o processo!
                System.out.println("ERRO NO UPLOAD DA IMAGEM: " + e.getMessage());
            }
        }
        // 2. Salva a placa no banco de dados (com foto ou sem foto, ela será salva!)
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