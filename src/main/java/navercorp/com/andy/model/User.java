package navercorp.com.andy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
public class User {
    String id;
    String name;
    String area;
    String img;
    int manner;
}
