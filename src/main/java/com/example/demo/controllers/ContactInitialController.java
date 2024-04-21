package com.example.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/contact")
@RequiredArgsConstructor
public class ContactInitialController {



    @GetMapping("/world")
    public String getIndexPage(Model model) {
        model.addAttribute("input", "!");
        return "index";
    }


    @GetMapping("/{name}")
    public String getIndexPage(Model model, @PathVariable String name) {
        model.addAttribute("input", name);
        return "index";
    }

    @GetMapping("/")
    public String getIndexPage2(Model model, @RequestParam String name) {
        model.addAttribute("input", name);
        return "index";
    }
}

