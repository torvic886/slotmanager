package com.slotmanager.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "SlotManager API está corriendo!";
    }
}
