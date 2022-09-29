package com.example.meby98.initprojectkafka.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.meby98.initprojectkafka.DTO.AddressDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String city;
    @Column
    private String state;
    @Column
    private int zipcode;

    public Address(AddressDTO address) {
        this.city = address.getCity();
        this.state = address.getState();
        this.zipcode = address.getZipcode();
    }
}
