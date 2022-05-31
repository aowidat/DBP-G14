package com.dpb.store.services.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a class used by Jackson to deserialize a tag called dvdspec
 */
@Setter
@Getter
@NoArgsConstructor
public class DVDspec {
    private String aspectratio;
    private String format;
    private String regioncode;
    private String releasedate;
    private String runningtime;
    private String theatr_release;
    private String upc;
}
