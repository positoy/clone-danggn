package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.GoodsListItem;
import navercorp.com.andy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class GoodsController {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/goods")
    public String getGoods(Model model) {
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

        model.addAttribute("list", list);
        return "home";
    }

    @GetMapping("/goods/{id}")
    public String getGoods(@PathVariable String id, Model model) {
        System.out.println("goods id : " + id);
        // TODO : add user field inside the goods and get a user from the goods
        Goods goods = goodsRepository.getGoods(id);
        User user = userRepository.getUser(goods.getUserid());

        // TODO handle null exception
        if (goods == null || user == null)
            System.out.println("null error has occured.");
        System.out.println(goods);
        System.out.println(user);
        model.addAttribute("goods", goods);
        model.addAttribute("user", user);
        return "item";
    }

    @PostMapping("/goods")
    public String postGoods(Goods good) {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime() / 1000;
        good.setTimestamp(String.valueOf(timestamp));
        good.setId(good.getUserid() + "_" + timestamp);
        goodsRepository.save(good);
        return "redirect:/goods";
    }
}
