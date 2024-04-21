package com.example.demo.service;

import com.example.demo.dao.ContactDAO;
import com.example.demo.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactDAO contactDAO;

    public List<Contact> getAll() {
        return contactDAO.findAll();
    }

    public void createContact(Contact contact) {
        contactDAO.insert(contact);
    }
}