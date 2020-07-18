package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.GoodsListItem;
import navercorp.com.andy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class GoodsService {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    UserRepository userRepository;

    ArrayList<GoodsListItem> getDummy() {
        ArrayList<GoodsListItem> list = goodsRepository.getDummy();

        long timestamp = new Timestamp(System.currentTimeMillis()).getTime() / 1000;
        for (int i=0; i<list.size(); i++) {
            long itemTimestamp = Long.parseLong(list.get(i).getTimestamp());
            Date itemDate = new Timestamp(itemTimestamp*1000);

            String val = "";
            if (Util.isOnTheSameDate(itemDate)) {
                long hour = (timestamp - itemTimestamp) / 3600;
                long min = (timestamp - itemTimestamp) % 3600 / 60;
                long sec = (timestamp - itemTimestamp) % 3600 % 60;

                if (hour != 0)
                    val = " · " + hour + "시간 전";
                else if (min != 0)
                    val = " · " + min + "분 전";
                else
                    val = " · " + sec + "초 전";

            } else {
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
                cal.setTime(itemDate);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);
                val = " · " + month + "월" + day + "일";
            }

            list.get(i).setTimestamp(val);
        }

        return list;
    }

    void saveGoods(Goods good) {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime() / 1000;
        good.setTimestamp(String.valueOf(timestamp));
        good.setId(good.getUserid() + "_" + timestamp);

        if (good.getUserid().isEmpty()) {
            System.out.println("no userid so is set 0");
            good.setUserid("0");
        }
        goodsRepository.save(good);
    }

    Goods getGoods(String id) {
        return goodsRepository.getGoods(id);
    }

    User getUser(String id) {
        return userRepository.getUser(id);
    }



}
