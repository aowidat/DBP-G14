package com.dpb.store.services.other;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class ContactPerson {
    private String Street;
    private Integer building_number;
    private String zip;
    private String city;
    private LocalDate birthdate;
    private String phone;
    private String email;

}
