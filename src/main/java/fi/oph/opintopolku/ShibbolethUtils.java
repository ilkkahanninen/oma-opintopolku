package fi.oph.opintopolku;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.nio.charset.Charset;

public class ShibbolethUtils {
    final static DateTimeFormatter formatter = DateTimeFormat.forPattern("ddMMYY");

    public static LocalDate parseDateFromHetu(String hetu) {
        if (hetu != null) {
            return formatter.parseLocalDate(hetu.substring(0, 6));
        }
        return null;
    }

    public static String parseDisplayName(String etunimet, String sukunimi) {
        // Dekoodataan etunimet ja sukunimi manuaalisesti, koska shibboleth välittää ASCII-enkoodatut request headerit UTF-8 -merkistössä
        Charset windows1252 = Charset.forName("Windows-1252");
        Charset utf8 = Charset.forName("UTF-8");
        StringBuilder builder = new StringBuilder();

        if (etunimet != null) {
            etunimet = new String(etunimet.getBytes(windows1252), utf8);
            builder.append(etunimet);
        }

        if (etunimet != null && sukunimi != null) {
            builder.append(" ");
        }

        if (sukunimi != null) {
            sukunimi = new String(sukunimi.getBytes(windows1252), utf8);
            builder.append(sukunimi);
        }

        return builder.toString();
    }

}
