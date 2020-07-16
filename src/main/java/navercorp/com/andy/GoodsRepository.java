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
        goods.add(new Goods("0", "라오스 다이어리","신동","1594645127700",2000,0,5, "/img/001_1.jpg", "디지털/가전", "하이루"));
        goods.add(new Goods("1", "자이글","권선동","1594645127642",20000,0,0, "/img/002_1.jpg", "디지털/가전", "하이루"));
        goods.add(new Goods("2", "라오스 다이어리","신동","1594645127700",2000,0,5, "/img/001_1.jpg", "디지털/가전", "하이루"));
        goods.add(new Goods("3", "삼성 파워건 VS80M8090KC 싸게 팔아요","수원시 팔달구 인계동","1594645127622",270000,1,3, "/img/003_1.jpg", "디지털/가전", "하이루"));
        goods.add(new Goods("4", "삼성 파워건 무선청소기 새 배터리!","수원시 팔달구 인계동","594645127615",150000,0,3, "/img/004_1.jpg", "디지털/가전", "하이루"));
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
}
