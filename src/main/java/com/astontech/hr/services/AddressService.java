package com.astontech.hr.services;

import com.astontech.hr.domain.Address;
import com.astontech.hr.domain.Contact;

public interface AddressService {

    Iterable<Address> listAllAddresses();

    Address getAddressById(Integer id);

    Address saveAddress(Address address);

    Iterable<Address> saveAddressList  (Iterable<Address> addressIterable);

    void deleteAddress(Integer id);

}
