package com.ex10.HalProgLang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProgLangController {
    @Autowired
    ProgLangRepository progLangRepository;

    @PostMapping("/new")
    private ProgLang createProgLang (@RequestBody ProgLang progLang){
        return progLangRepository.saveAndFlush(progLang);
    }

    @GetMapping("/getAll")
    private List <ProgLang> readAll (@RequestParam int page, int size){
        return progLangRepository.findAll();
    }

    @PatchMapping("/updateInventor/{id}")
    private ProgLang updateInventor (
            @PathVariable Long id,
            @RequestBody ProgLang newInventor
    ) {
        if (newInventor != null) {
            newInventor.setInventor("Giò");
            return progLangRepository.saveAndFlush(newInventor);
        }
        return null;
    }

}
