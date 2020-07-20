package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.GoodsListItem;
import navercorp.com.andy.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class GoodsService {

    final static Logger logger = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    UserRepository userRepository;
    private Goods good;
    private ArrayList<String> files;

    ArrayList<GoodsListItem> getDummy() {
        ArrayList<GoodsListItem> list = goodsRepository.getDummy();

        long current_millis = new Timestamp(System.currentTimeMillis()).getTime();

        for (int i=0; i<list.size(); i++) {
            long item_millis = Long.parseLong(list.get(i).getTimestamp()) * 1000;
            String smartTimestamp = Util.getSmartTimestamp(current_millis, item_millis);
            list.get(i).setTimestamp(smartTimestamp);
        }
        return list;
    }

    Goods refineGoods(Goods good, User user) {
        good.setTimestamp(String.valueOf(new Timestamp(System.currentTimeMillis()).getTime() / 1000));
        good.setUserid(user.getId());
        good.setId(good.getTimestamp() + "_" + user.getId());
        return good;
    }

    void saveGoods(Goods good) {
        goodsRepository.save(good);
    }

    void setImgs(Goods good, ArrayList<String> imgs) {
        good.setImgs(imgs);
    }

    Goods getGoods(String id) {
        Goods good = null;
        try {
            good = (Goods) goodsRepository.getGoods(id).clone();
        } catch (CloneNotSupportedException e) {
            logger.info(e.getMessage());
            return null;
        }
        long current_millis = new Timestamp(System.currentTimeMillis()).getTime();
        long item_millis = Long.parseLong(good.getTimestamp()) * 1000;
        good.setTimestamp(Util.getSmartTimestamp(current_millis, item_millis));
        return good;
    }

    User getUser(Long id) {
        return userRepository.getUser(id);
    }

    User getUserWithGoodsId(String goodsid) {
        Goods good = goodsRepository.getGoods(goodsid);
        return userRepository.getUser(good.getUserid());
    }

}
