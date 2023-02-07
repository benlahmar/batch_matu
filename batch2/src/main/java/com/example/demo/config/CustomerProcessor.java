package com.example.demo.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.entities.Address;
import com.example.demo.entities.User;
import com.example.demo.entities.Userxml;
 

 
public class CustomerProcessor implements ItemProcessor<Userxml, User>{
 
    @Override
    public User process(Userxml item) throws Exception {
        User customer = new User();
        customer.setId(item.getId());
        customer.setNom(item.getNom());
//      customer.setAddresses(item.getAddress());
        customer.setAddresses(buildAddrss(item.getAddress()));
        return customer;
    }
    
    
 
//    private List<Address> buildAddress(List<AddressData> addresses) {
//        return addresses.stream().map(ad -> buildAddrss(ad)).collect(Collectors.toList());
//    }
// 
    private Address buildAddrss(Address ad) {
        Address address = new Address();
        address.setCity(ad.getCity());
        address.setStateCode(ad.getStateCode());
        address.setCountry(ad.getCountry());
        address.setZipCode(ad.getZipCode());
        address.setStateCode(ad.getStateCode());
        address.setStreetAddress(ad.getStreetAddress());
       
         
        return address;
    }
}