package com.vpr.app.service.impl;

import com.vpr.app.entity.Address;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.AddressRepository;
import com.vpr.app.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    public static final String INSTANCE_DOES_NOT_EXIST = "Address instance with id %d does not exist";
    private final AddressRepository addressRepository;

    @Override
    public Address findById(long id) {
        return addressRepository.findById(id).orElseThrow(() -> new VprEntityNotFoundException(String.format(INSTANCE_DOES_NOT_EXIST, id)));
    }//todo add new exceptions, docker dbg

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
