package services;

import com.astontech.hr.Application;
import com.astontech.hr.domain.Contact;
import com.astontech.hr.domain.Person;
import com.astontech.hr.services.ContactService;
import com.astontech.hr.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void contactServiceTest() {

        Contact contact = new Contact();
        contact.setEmail("tucktuck@gmail.com");
        contact.setPerson(employeeService.getEmployeeById(0));
        contact.setPhoneNumber("111-222-3333");

        //save contact, verify it has an ID after the save.
        assertNull(contact.getId());
        contactService.saveContact(contact);
        assertNotNull(contact.getId());

        //fetch
        Contact fetchedContact = contactService.getContactById(contact.getId());
        assertNotNull(fetchedContact);
        assertEquals(contact.getId(), fetchedContact.getId());

        //update
        fetchedContact.setPhoneNumber("444-555-6666");
        contactService.saveContact(fetchedContact);

        Contact updatedContact = contactService.getContactById(fetchedContact.getId());
        assertEquals(updatedContact.getPhoneNumber(), "444-555-6666");

        //delete
        contactService.deleteContact(fetchedContact.getId());
        assertNull(contactService.getContactById(fetchedContact.getId()));

    }

}
