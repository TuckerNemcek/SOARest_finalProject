package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Contact;
import com.astontech.hr.repositories.ContactRepository;
import com.astontech.hr.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Iterable<Contact> listAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(Integer id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Iterable<Contact> saveContactList(Iterable<Contact> contactIterable) {
        return contactRepository.save(contactIterable);
    }

    @Override
    public void deleteContact(Integer id) {
        contactRepository.delete(id);
    }
}
