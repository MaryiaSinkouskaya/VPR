package com.leverx.app.service;

import com.leverx.app.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    Address findById(long id);

    List<Address> findAll();
}
