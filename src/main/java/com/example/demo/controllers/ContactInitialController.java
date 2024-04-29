package com.example.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/demo")
@RequiredArgsConstructor
public class ContactInitialController {


    @GetMapping("/world")
    public String getIndexPage(Model model) {
        model.addAttribute("input", "Hello! Im new app");
        return "index";
    }


    @GetMapping("/{name}")
    public String getIndexPage(Model model, @PathVariable String name) {
        model.addAttribute("input", name);
        return "index";
    }
    ///?name=example
    @GetMapping("/")
    public String getIndexPage2(Model model, @RequestParam String name) {
        model.addAttribute("input", name);
        return "index";
    }
}

