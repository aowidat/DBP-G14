package com.dpb.store.services.parser;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DVDspec {
    private String aspectratio;
    private String format;
    private String regioncode;
    private String releasedate;
    private String runningtime;
    private String theatr_release;
    private String upc;

    public DVDspec() {

    }
}
