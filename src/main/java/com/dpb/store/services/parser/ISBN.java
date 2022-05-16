package com.dpb.store.services.parser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ISBN {
    private String val;

    public ISBN() {
    }

    public ISBN(String val) {
        this.val = val;
    }
}
