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

    public long createContact(Contact contact) {
        return contactDAO.insert(contact);
    }

    public int deleteContact(Long id) {
        return contactDAO.delete(id);
    }

    public int updateContact(Contact contact) {
        return contactDAO.update(contact);
    }

    public Contact getById(Long id) {
        return contactDAO.findById(id);
    }
}
