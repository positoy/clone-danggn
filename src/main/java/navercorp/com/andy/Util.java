package navercorp.com.andy;

import navercorp.com.andy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    final static Logger logger = LoggerFactory.getLogger(Util.class);

    public static void updateModelUserFromSession(HttpServletRequest request, Model model) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            logger.warn("session has no user");
        } else {
            logger.info("session user : " + user.getId());
            model.addAttribute("user", user);
        }
    }

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

    public static String getSmartTimestamp(long current_millis, long item_millis) {

        String ret = "";

        Date itemDate = new Timestamp(item_millis);
        long current_timestamp = current_millis / 1000;
        long item_timestamp = item_millis / 1000;

        if (Util.isOnTheSameDate(itemDate)) {
            long hour = (current_timestamp - item_timestamp) / 3600;
            long min = (current_timestamp - item_timestamp) % 3600 / 60;
            long sec = (current_timestamp - item_timestamp) % 3600 % 60;

            if (hour != 0)
                ret = "" + hour + "시간 전";
            else if (min != 0)
                ret = "" + min + "분 전";
            else
                ret = "" + sec + "초 전";

        } else {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
            cal.setTime(itemDate);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            ret = "" + month + "월" + day + "일";
        }

        return ret;
    }
}
