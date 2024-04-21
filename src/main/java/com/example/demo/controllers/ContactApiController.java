package com.example.demo.controllers;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/contact")
@RequiredArgsConstructor
public class ContactApiController {

    private final ContactService contactService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("messages", contactService.getAll());
        model.addAttribute("toSend", new Contact());
        return "contactForm";
    }

    @PostMapping
    public String sendMessage(Model model, Contact contact) {
        contactService.createContact(contact);
        return getAll(model);
    }
}

