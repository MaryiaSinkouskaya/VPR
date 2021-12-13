package com.leverx.app.service.impl;

import com.leverx.app.entity.Address;
import com.leverx.app.repository.AddressRepository;
import com.leverx.app.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address findById(long id) {
        return addressRepository.findById(id).orElse(new Address());
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
