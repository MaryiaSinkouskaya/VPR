package com.vpr.app.service;

import com.vpr.app.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    Address findById(long id);

    List<Address> findAll();

    Address create(Address address);

    Address update(Address address);

    void delete(long id);
}
