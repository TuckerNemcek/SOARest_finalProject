package services;
import com.astontech.hr.Application;
import com.astontech.hr.domain.Address;
import com.astontech.hr.services.AddressService;
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

public class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void addressServiceTest() {

        Address address = new Address();
        address.setEmployee(employeeService.getEmployeeById(1));
        address.setCity("Welch");
        address.setStreet("22799");
        address.setZip("55089");

        //save contact, verify it has an ID after the save.
        assertNull(address.getId());
        addressService.saveAddress(address);
        assertNotNull(address.getId());

        //fetch
        Address fetchedAddress = addressService.getAddressById(address.getId());
        assertNotNull(fetchedAddress);
        assertEquals(address.getId(), fetchedAddress.getId());

        //update
        fetchedAddress.setCity("Welch");
        addressService.saveAddress(fetchedAddress);

        Address updatedAddress = addressService.getAddressById(fetchedAddress.getId());
        assertEquals(updatedAddress.getCity(),"Welch");

        addressService.deleteAddress(fetchedAddress.getId());
        assertNull(addressService.getAddressById(fetchedAddress.getId()));
    }

}
