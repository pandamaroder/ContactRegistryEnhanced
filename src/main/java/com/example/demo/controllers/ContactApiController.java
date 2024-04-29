package com.example.demo.controllers;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("api/contact")
@RequiredArgsConstructor
public class ContactApiController {

    private final ContactService contactService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("contacts", contactService.getAll());
        model.addAttribute("toSend", new Contact());
        return "contactForm";
    }

    @PostMapping
    public String sendMessage(Model model, Contact contact) {
        long contactNewId = contactService.createContact(contact);
        log.info("New added contact {}", contactNewId);
        return getAll(model);
    }
}

