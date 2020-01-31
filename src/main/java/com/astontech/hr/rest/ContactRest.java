package com.astontech.hr.rest;

import com.astontech.hr.domain.Contact;
import com.astontech.hr.domain.Employee;
import com.astontech.hr.services.ContactService;
import com.astontech.hr.services.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contact")
public class ContactRest {

    private Logger log = Logger.getLogger(ContactRest.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private EmployeeService employeeService;

    //Returns all contacts
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Contact> getAll() { return contactService.listAllContacts(); }

    //GET BY ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Contact getById(@PathVariable int id) {
        return contactService.getContactById(id);
    }

    //SAVE
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact) {
        //linkEmployee(contact);
        Employee employee = employeeService.getEmployeeById(contact.getEmployee().getId());
        contact.setPerson(employee);
        return contactService.saveContact(contact);}

        //DELETE
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable int id) {
        boolean result = false;
        try {
            contactService.deleteContact(id);
            result = true;
        } catch (Exception ex) {
            log.error((ex));
        }
        return result;
    }
}
