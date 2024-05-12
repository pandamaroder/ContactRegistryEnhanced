package com.example.demo.controllers;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add/contact")
    public String createContact(Model model, Contact contact) {
        long contactNewId = contactService.createContact(contact);
        log.info("New added contact {}", contactNewId);
        return getAll(model);
    }

    @GetMapping("/add_contact_form.html")
    public String showAddContactForm() {
        return "add_contact_form"; // Возвращаем имя HTML файла без расширения
    }

    @PostMapping("/delete")
    public String deleteContacts(Model model, @RequestParam("selectedContactIds") List<String> ids) {
        ids.stream()
                .map(Long::valueOf)
                .mapToInt(contactService::deleteContact)
                .forEach(count -> log.info("deleted contact count {}", count));
        return getAll(model);
    }

    @PutMapping("/update")
    public String updateContact(Model model, @ModelAttribute Contact contact) {
        int updatedCount = contactService.updateContact(contact);
        log.info("Updated contact count: {}", updatedCount);
        return getAll(model);
    }
}

