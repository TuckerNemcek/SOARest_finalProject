package com.astontech.hr.rest;

import com.astontech.hr.domain.Address;
import com.astontech.hr.domain.Employee;
import com.astontech.hr.services.AddressService;
import com.astontech.hr.services.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressRest {

    private Logger log = Logger.getLogger(AddressRest.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private EmployeeService employeeService;

    //Return all addresses
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Address> getAll() { return addressService.listAllAddresses(); }

    //GET BY ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Address getById(@PathVariable int id) { return addressService.getAddressById(id);}

    //SAVE
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Address save(@RequestBody Address address) {
        Employee employee = employeeService.getEmployeeById(address.getEmployee().getId());
        address.setEmployee(employee);
        return addressService.saveAddress(address);}

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable int id) {
        boolean result = false;
        try {
            addressService.deleteAddress(id);
            result = true;
        } catch (Exception ex) {
            log.error(ex);
        }
        return result;
    }
}
