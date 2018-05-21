import fi.oph.opintopolku.ShibbolethUtils;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParseShibbolethHeadersTest {

    @Test
    void parseDateFromHetuString() {
        LocalDate date = ShibbolethUtils.parseDateFromHetu("240199-1234");
        assertEquals(date.getDayOfMonth(), 24);
        assertEquals(date.getMonthOfYear(), 1);
        assertEquals(date.getYear(), 1999);
    }
}
