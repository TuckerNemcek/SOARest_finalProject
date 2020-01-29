package com.astontech.hr.repositories;

import com.astontech.hr.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
}
