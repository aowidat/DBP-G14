package com.dpb.store.services.parser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DVDspec {
    private String aspectratio;
    private String format;

    public DVDspec(String aspectratio, String format, String regioncode, String releasedate, String runningtime, String theatr_release, String upc) {
        this.aspectratio = aspectratio;
        this.format = format;
        this.regioncode = regioncode;
        this.releasedate = releasedate;
        this.runningtime = runningtime;
        this.theatr_release = theatr_release;
        this.upc = upc;
    }
    public DVDspec(){

    }

    private String regioncode;
    private String releasedate;
    private String runningtime;
    private String theatr_release;
    private String upc;
}
