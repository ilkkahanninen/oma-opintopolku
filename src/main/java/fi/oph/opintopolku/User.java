package fi.oph.opintopolku;

import lombok.Data;

public @Data class User {
    private String name;
    private String birthDay;
    private String personOid;
    private String hetu;
    private boolean usingValtuudet;
}
