package com.ex3.name;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class NameController {
    @GetMapping("/")
    public String nome(
        @RequestParam (value = "name") String name) {
        return name;
    }

    @PostMapping("/reversed/{name}")
        public String reverse (
                @PathVariable String name,
                @RequestBody String rvrs)
    {
        StringBuilder eman = new StringBuilder(rvrs);
        return eman.reverse().toString();
    }

}


