package com.dpb.store.services.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a class used by Jackson to deserialize a tag called package
 */
@Setter
@Getter
@NoArgsConstructor
public class Package {
    private String weight;
    private String height;
    private String length;
}
