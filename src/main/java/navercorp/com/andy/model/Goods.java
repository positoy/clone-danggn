package navercorp.com.andy.model;

import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Goods implements Cloneable {
    // GoodsListItem
    String id;
    String title;
    String area;
    String timestamp;
    int price;
    int chat;
    int like;
    // one item in the GoodsListItem
    ArrayList<String> imgs;
    // only in the Goods
    String category;
    String body;
    String userid;

    public Goods(String id, String title, String area, String timestamp, int price, int chat, int like, String img, String category, String body, String userid) {
        this.id = id;
        this.title = title;
        this.area = area;
        this.timestamp = timestamp;
        this.price = price;
        this.chat = chat;
        this.like = like;
        this.imgs = new ArrayList<>();
        if (!img.isEmpty()) {
            imgs.add(img);
        }
        this.category = category;
        this.body = body;
        this.userid = userid;
    }

    public GoodsListItem getListItem() {
        String imgUrl = imgs.size() != 0 ? imgs.get(0) : "";
        return new GoodsListItem(id, title, area, timestamp, price, chat, like, imgUrl);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Goods good = (Goods) super.clone();
        good.imgs = (ArrayList<String>) this.imgs.clone();
        return good;
    }
}
