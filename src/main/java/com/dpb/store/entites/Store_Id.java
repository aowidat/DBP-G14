package com.dpb.store.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@Getter
@Setter
public class Store_Id implements Serializable {
    private String name;
    private String street;
    private int zip;
}
