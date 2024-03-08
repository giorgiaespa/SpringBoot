package com.ex10.HalProgLang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ProgLangController {

    @Autowired
    private ProgLangService progLangService;

    @PostMapping("/new")
    private ProgLang createProgLang (@RequestBody ProgLang progLang){
        return progLangService.newProgLang(progLang);
    }

    @GetMapping("/getAllPaginated")
    public Page<ProgLang> readAll(){
        return ProgLangService.pagination(Pageable.ofSize(2));
    }

    @PatchMapping("/updateInventor/{id}")
    private ProgLang updateInventor (
            @PathVariable Long id,
            @RequestBody ProgLang newInventor
    ) {
        return progLangService.changeInventor(newInventor);
    }

}
