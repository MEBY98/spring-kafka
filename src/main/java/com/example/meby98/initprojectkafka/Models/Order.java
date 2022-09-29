package com.example.meby98.initprojectkafka.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.meby98.initprojectkafka.DTO.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    private Long orderid;

    @Column
    private Long ordertime;
    @Column
    private String itemid;
    @Column
    private double orderunits;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Address address;

    public Order(OrderDTO newOrder) {
        this.address = new Address(newOrder.getAddress());
        this.itemid = newOrder.getItemid();
        this.orderid = newOrder.getOrderid();
        this.ordertime = newOrder.getOrdertime();
        this.orderunits = newOrder.getOrderunits();
    }
}
