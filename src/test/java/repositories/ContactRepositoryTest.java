package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Contact;
import com.astontech.hr.repositories.ContactRepository;
import com.astontech.hr.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSave() {
        Contact contact = new Contact();
        contact.setPerson(employeeRepository.findOne(0));
        contact.setPhoneNumber("6513036867");
        contact.setEmail("tuck@tuck.com");

        assertNull(contact.getId());
        contactRepository.save(contact);
        assertNotNull(contact.getId());

        //fetch
        Contact fetchedContact = contactRepository.findOne(contact.getId());
        assertNotNull(fetchedContact);
        assertEquals(contact.getId(), fetchedContact.getId());

        Contact fetchedUpdatedContact = contactRepository.findOne((fetchedContact.getId()));
        assertEquals("6513036867", fetchedUpdatedContact.getPhoneNumber());
    }

}
