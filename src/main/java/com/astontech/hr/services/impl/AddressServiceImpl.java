package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Address;
import com.astontech.hr.repositories.AddressRepository;
import com.astontech.hr.repositories.EmployeeRepository;
import com.astontech.hr.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Iterable<Address> listAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Integer id) {
        return addressRepository.findOne(id);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Iterable<Address> saveAddressList(Iterable<Address> addressIterable) {
        return addressRepository.save(addressIterable);
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepository.delete(id);
    }
}
