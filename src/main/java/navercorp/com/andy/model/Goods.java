package navercorp.com.andy;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Goods {
    String title;
    String category;
    String price;
    String body;
}
