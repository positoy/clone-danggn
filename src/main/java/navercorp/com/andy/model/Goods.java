package navercorp.com.andy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
@Entity
@Table(name ="goods")
public class Goods implements Cloneable {
    // GoodsListItem
    @Id
    @Column(length = 30)
    String id;

    @Column(length = 50)
    String title;

    @Column(length = 20)
    String area;

    @Column(length = 20)
    String timestamp;

    @Column
    Integer price;

    @Column
    Integer chat;

    @Column
    Integer liked;

    // one item in the GoodsListItem
    @Column(length = 100)
    String img;

    // only in the Goods
    @Column(length = 30)
    String category;

    @Column(length = 300)
    String body;

    @Column
    Long userid;
}
