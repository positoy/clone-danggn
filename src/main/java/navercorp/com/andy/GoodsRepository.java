package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.GoodsListItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class GoodsRepository {

    ArrayList<Goods> goods;

    public GoodsRepository() {
        goods = new ArrayList<>();
        goods.add(new Goods("0", "라오스 다이어리","신동","1594645127",2000,0,5, "/img/goods/001_1.jpg", "디지털/가전", "안녕", "0"));
        goods.add(new Goods("1", "자이글","권선동","1594645127",20000,0,0, "/img/goods/002_1.jpg", "디지털/가전", "하세요", "1"));
        goods.add(new Goods("2", "라오스 다이어리","신동","1594645127",2000,0,5, "/img/goods/001_1.jpg", "디지털/가전", "여기", "2"));
        goods.add(new Goods("3", "삼성 파워건 VS80M8090KC 싸게 팔아요","수원시 팔달구 인계동","1594645127",270000,1,3, "/img/goods/003_1.jpg", "디지털/가전", "들어가는 내용은", "0"));
        goods.add(new Goods("4", "삼성 파워건 무선청소기 새 배터리!","수원시 팔달구 인계동","594645127",150000,0,3, "/img/goods/004_1.jpg", "디지털/가전", "본문입니다", "1"));
        goods.add(new Goods("5", "냉무 나눔합니","인계동","594645127",150000,0,3, "", "디지털/가전", "본문입니다", "1"));
    }

    public ArrayList<GoodsListItem> getDummy() {
        ArrayList<GoodsListItem> list = new ArrayList<>();
        for (Goods good : goods) {
            list.add(good.getListItem());
        }
        return list;
    }

    public Goods getGoods(String goodsid) {
        for (Goods good : goods) {
            if (good.getId().equals(goodsid))
                return good;
        }
        System.out.println("Couldn't find any goods with id, " + goodsid);
        return null;
    }

    // TODO none of goods member must be empty
    public void save(Goods good) {
        if (!validate(good))
            fill(good);
        goods.add(good);
    }

    boolean validate(Goods good) {
        return good.getId() != null &&
                good.getTitle() != null &&
                good.getArea() != null &&
                good.getTimestamp() != null &&
                good.getImgs() != null &&
                good.getBody() != null &&
                good.getUserid() != null;
    }

    void fill(Goods good) {
        if (good.getId() == null) good.setId("");
        if (good.getTitle() == null) good.setTitle("");
        if (good.getArea() == null) good.setArea("");
        if (good.getTimestamp() == null) good.setTimestamp("");
        if (good.getImgs() == null) good.setImgs(new ArrayList<>());
        if (good.getBody() == null) good.setBody("");
        if (good.getUserid() == null) good.setUserid("");
    }
}
