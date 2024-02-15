package com.ex5.name;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/v1")
@Tag (name= "Book controller")
public class NameController {
    @GetMapping("/")
    @Operation(description = "prints the name straight")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "444", description = "The name is missing")
    })
    @Parameter(name = "Name", description = "write down the name")
    public String nome(
            @RequestParam (value = "name") String name) {
        return name;
    }

    @PostMapping("/reversed/{name}")
    @Operation(description = "prints the name in reverse")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "443", description = "Couldn't execute the command")
    })
    @Parameter(name = "Eman", description = "reversed name")
    public StringBuilder reverse (
            @RequestParam String rvrs)
    {
        StringBuilder eman = new StringBuilder(rvrs);
        return eman.reverse();
    }

}
