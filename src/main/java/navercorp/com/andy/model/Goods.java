package navercorp.com.andy.model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
@Table(name ="goods")
public class Goods implements Cloneable {
    // GoodsListItem
    @Id
    String id;
    String title;
    String area;
    String timestamp;
    int price;
    int chat;
    int liked;
    // one item in the GoodsListItem
    ArrayList<String> imgs;
    // only in the Goods
    String category;
    String body;
    Long userid;

    public Goods(String id, String title, String area, String timestamp, int price, int chat, int liked, String img, String category, String body, Long userid) {
        this.id = id;
        this.title = title;
        this.area = area;
        this.timestamp = timestamp;
        this.price = price;
        this.chat = chat;
        this.liked = liked;
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
        return new GoodsListItem(id, title, area, timestamp, price, chat, liked, imgUrl);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Goods good = (Goods) super.clone();
        good.imgs = (ArrayList<String>) this.imgs.clone();
        return good;
    }
}
