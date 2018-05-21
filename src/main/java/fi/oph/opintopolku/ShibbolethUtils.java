package fi.oph.opintopolku;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ShibbolethUtils {
    final static DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMYY");

    public static LocalDate parseDateFromHetu(String hetu) {
        return formatter.parseLocalDate(hetu.substring(0, 6));
    }

}
