package navercorp.com.andy;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static boolean isOnTheSameDate(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
        int now_year = cal.get(Calendar.YEAR);
        int now_month = cal.get(Calendar.MONTH);
        int now_day = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return (now_year == year) &&
                (now_month == month) &&
                (now_day == day);
    }
}
