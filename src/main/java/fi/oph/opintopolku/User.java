package fi.oph.opintopolku;

import lombok.Data;
import org.joda.time.LocalDate;

public @Data class User {
    private String name;
    private String birthDay;
    private String personOid;
    private String hetu;
}
