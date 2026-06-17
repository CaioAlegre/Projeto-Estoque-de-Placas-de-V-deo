package placas.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import placas.java.model.Placavideo;
import placas.java.model.PlacavideoService;

import java.util.List;

@RestController
@RequestMapping("/rest/placas")
public class PlacaRestController {

    @Autowired
    private PlacavideoService service;

    @GetMapping
    public List<Placavideo> listarRest() {
        return service.listarPlacas();
    }

    @PostMapping
    public Placavideo inserirRest(@RequestBody Placavideo placa) {
        return service.salvar(placa);
    }
}