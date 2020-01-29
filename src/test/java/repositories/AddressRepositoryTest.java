package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Address;
import com.astontech.hr.domain.Employee;
import com.astontech.hr.repositories.AddressRepository;
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
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSave() {
        Address address = new Address();
        address.setEmployee(employeeRepository.findOne(0));
        address.setZip("55089");
        address.setStreet("55448 22nd Ave");
        address.setCity("Welch");

        assertNull(address.getId());
        addressRepository.save(address);
        assertNotNull(address.getId());

        //fetch
        Address fetchedAddress =  addressRepository.findOne(address.getId());
        assertNotNull(fetchedAddress);
        assertEquals(address.getId(), fetchedAddress.getId());

        Address fetchedUpdatedAddress = addressRepository.findOne(fetchedAddress.getId());
        assertEquals("Welch", fetchedUpdatedAddress.getCity());

    }

}
