package navercorp.com.andy.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class A {
    String data;

    public A(String data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

class GoodsTest {

    @Test
    public void testGoods() {
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("0", "라오스 다이어리","신동","1594645127700",2000,0,5, "/img/001_1.jpg", "디지털/가전", "안녕", "0"));
        goods.add(new Goods("1", "자이글","권선동","1594645127642",20000,0,0, "/img/002_1.jpg", "디지털/가전", "하세요", "1"));
        goods.add(new Goods("2", "라오스 다이어리","신동","1594645127700",2000,0,5, "/img/001_1.jpg", "디지털/가전", "여기", "2"));
        goods.add(new Goods("3", "삼성 파워건 VS80M8090KC 싸게 팔아요","수원시 팔달구 인계동","1594645127622",270000,1,3, "/img/003_1.jpg", "디지털/가전", "들어가는 내용은", "0"));

        Assertions.assertEquals(goods.get(0).getArea(), goods.get(0).getListItem().getArea());
        Assertions.assertEquals(goods.get(0).getArea(), new String(goods.get(0).getListItem().getArea()));
        System.out.println(goods.get(0).getArea() == new String(goods.get(0).getListItem().getArea()));
    }

    @Test
    public void testGoodsListItem() {
        Goods good = new Goods("0", "라오스 다이어리","신동","1594645127700",2000,0,5, "/img/001_1.jpg", "디지털/가전", "안녕", "0");
        GoodsListItem item = good.getListItem();
        item.setTimestamp("7/18");
        Assertions.assertNotEquals(good.getTimestamp(), item.getTimestamp());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Goods good = new Goods("0", "라오스 다이어리","신동","1594645127700",2000,0,5, "/img/001_1.jpg", "디지털/가전", "안녕", "0");
        GoodsListItem gooditem = good.getListItem();
        gooditem.setTimestamp("hello");
        Assertions.assertNotEquals(good.getTimestamp(), gooditem.getTimestamp());
    }
}