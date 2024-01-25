package com.example.QParamsAndGets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")

public class v1 {
    @GetMapping(path = "/ciao")
    public String dati (@RequestParam String nome, String provincia){
        return "Ciao " + nome + ", com'è il tempo in " + provincia + "?";
    }
}
