package tpi.transporte.operaciones.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/") // El gateway ya quitó el prefijo /operaciones
public class TestController {

    @GetMapping("/test")
    public String test() {
        // Este mensaje nos confirmará que la petición llegó a ESTE servicio
        return "Respuesta desde Operaciones Service!";
    }
}