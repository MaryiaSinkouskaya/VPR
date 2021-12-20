package com.vpr.app.service.impl;

import com.vpr.app.entity.Address;
import com.vpr.app.repository.AddressRepository;
import com.vpr.app.service.AddressService;
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

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void delete(long id) {
        addressRepository.deleteById(id);
    }
}
