package navercorp.com.andy;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    public void testIsOnTheSameDate() {
        final long millis_in_a_day = 24*60*60*1000;
        assertTrue(Util.isOnTheSameDate(new Date()));
        assertFalse(Util.isOnTheSameDate(new Date(new Timestamp(System.currentTimeMillis() - millis_in_a_day).getTime())));
    }
}