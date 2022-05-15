package com.dpb.store.services.parser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Musicspec {
    private String binding;
    private String format;
    private String num_discs;
    private String releasedate;
    private String upc;


    public Musicspec(){

    }
    public Musicspec(String binding, String format, String num_discs, String releasedate, String upc) {
        this.binding = binding;
        this.format = format;
        this.num_discs = num_discs;
        this.releasedate = releasedate;
        this.upc = upc;
    }
}
