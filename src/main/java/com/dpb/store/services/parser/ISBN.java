package com.dpb.store.services.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a class used by Jackson to deserialize a tag called ISBN
 */
@Getter
@Setter
@NoArgsConstructor
public class ISBN {
    private String val;

    public ISBN(String val) {
        this.val = val;
    }
}
