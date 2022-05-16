package com.dpb.store.services.parser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Package {
    public Package(){

    }
    public Package(String weight, String height, String length) {
        this.weight = weight;
        this.height = height;
        this.length = length;
    }

    private String weight;
    private String height;
    private String length;
}
