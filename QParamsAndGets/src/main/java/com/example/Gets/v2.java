package com.example.Gets;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")

public class v2 {

    @GetMapping(path = "/ciao/{provincia}")
    public User dati (
            @PathVariable String provincia,
            @RequestParam(value = "nome") String nome)
    {String saluto = "Ciao " + nome + ", com'è il tempo in " + provincia + "?";
        return new User (nome, provincia, saluto);
    }
}