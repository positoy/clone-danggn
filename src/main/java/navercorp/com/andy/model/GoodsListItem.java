package navercorp.com.andy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
public class GoodsListItem {
    String id;
    String title;
    String area;
    String timestamp;
    int price;
    int chat;
    int like;
// ArrayList in the Goods
    String img;
}
