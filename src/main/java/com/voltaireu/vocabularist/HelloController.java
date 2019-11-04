package com.voltaireu.vocabularist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping({"/hello"})
    public String firstPage(){
        return "Hello World!";
    }
}
