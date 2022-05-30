package com.dpb.store.services.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a class used by Jackson to deserialize a tag called musicspec
 */
@Setter
@Getter
@NoArgsConstructor
public class Musicspec {
    private String binding;
    private Format format;
    private String num_discs;
    private String releasedate;
    private String upc;
}
