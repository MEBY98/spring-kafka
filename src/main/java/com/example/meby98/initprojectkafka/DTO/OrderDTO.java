package com.example.meby98.initprojectkafka.DTO;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @NotNull
    private Long orderid;
    @NotNull
    private Long ordertime;
    @NotNull
    private String itemid;
    @NotNull
    private double orderunits;
    @NotNull
    private AddressDTO address;
}
