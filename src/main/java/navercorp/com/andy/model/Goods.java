package navercorp.com.andy.model;

import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Goods {
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

    public Goods(String id, String title, String area, String timestamp, int price, int chat, int like, String img, String category, String body) {
        this.id = id;
        this.title = title;
        this.area = area;
        this.timestamp = timestamp;
        this.price = price;
        this.chat = chat;
        this.like = like;
        this.imgs = new ArrayList<>();
        imgs.add(img);
        this.category = category;
        this.body = body;
    }

    public GoodsListItem getListItem() {
        return new GoodsListItem(id, title, area, timestamp, price, chat, like, imgs.get(0));
    }

}
