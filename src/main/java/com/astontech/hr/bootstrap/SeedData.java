package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.Contact;
import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.domain.Employee;
import com.astontech.hr.services.ContactService;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.ElementTypeService;
import com.astontech.hr.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    ContactService contactService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        generateElementAndElementTypes();
        generateEmployee();
        generateContacts();
    }

    private void generateElementAndElementTypes()    {

        ElementType laptopType = new ElementType("Laptop");

        List<Element> elementListLaptop = new ArrayList<>();
        elementListLaptop.add(new Element("Acer"));
        elementListLaptop.add(new Element("Dell"));
        elementListLaptop.add(new Element("Samsung"));
        elementListLaptop.add(new Element("Apple"));
        elementListLaptop.add(new Element("Asus"));

        laptopType.setElementList(elementListLaptop);

        elementTypeService.saveElementType(laptopType);

        ElementType phoneType = new ElementType("Phone");

        List<Element> elementListPhone = new ArrayList<>();
        elementListPhone.add(new Element("iPhone"));
        elementListPhone.add(new Element("Galaxy"));
        elementListPhone.add(new Element("LG"));
        elementListPhone.add(new Element("Pixel"));
        elementListPhone.add(new Element("Trac"));

        phoneType.setElementList(elementListPhone);

        elementTypeService.saveElementType(phoneType);

    }

    private void generateEmployee() {

        Employee employee = new Employee();
        employee.setFirstName("Tucker");
        employee.setLastName("Nemcek");
        employee.setBackground("JavaDeveloper");
        employeeService.saveEmployee(employee);

        Employee employee1 = new Employee();
        employee1.setFirstName("Tony");
        employee1.setLastName("Soprano");
        employee1.setBackground("Coke Lord");
        employeeService.saveEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("The");
        employee2.setLastName("Godfather");
        employee2.setBackground("Godfather");
        employeeService.saveEmployee(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Vladimir");
        employee3.setLastName("Putin");
        employee3.setBackground("Mafioso");
        employeeService.saveEmployee(employee3);

    }

    private void generateContacts() {

        Contact tuckerContact = new Contact();
        tuckerContact.setEmail("tucker@tuck.com");
        tuckerContact.setPhoneNumber("651-301-5752");
        tuckerContact.setPerson(employeeService.getEmployeeById(1));
        contactService.saveContact(tuckerContact);

        Contact tonyContact = new Contact();
        tonyContact.setEmail("i<3mySeptup@gmail.com");
        tonyContact.setPhoneNumber("885-522-7787");
        tonyContact.setPerson(employeeService.getEmployeeById(2));
        contactService.saveContact(tonyContact);

        Contact godFatherContact = new Contact();
        godFatherContact.setEmail("cantrefuse@msn.com");
        godFatherContact.setPhoneNumber("662-638-963");
        godFatherContact.setPerson(employeeService.getEmployeeById(3));
        contactService.saveContact(godFatherContact);

        Contact vladContact = new Contact();
        vladContact.setEmail("nodissidents@hotmail.com");
        vladContact.setPhoneNumber("154-522-6969");
        vladContact.setPerson(employeeService.getEmployeeById(4));
        contactService.saveContact(vladContact);


    }
}

