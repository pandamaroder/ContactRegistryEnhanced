package com.example.demo.dao;

import com.example.demo.model.Contact;

import java.util.List;

public interface ContactDAO {
    Contact findById(int id);
    List<Contact> findAll();
    List<Contact> findByName(String title);

    // CRUD
    long insert(Contact Contact);
    void update(Contact Contact);
    void delete(int id);

}

