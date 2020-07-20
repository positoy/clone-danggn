package navercorp.com.andy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
@Entity
@Table (name = "users")
public class User {
    // Naver Profile API
    @Id
    Long id;
    String nickname;
    String profile_image;
    String name;
    // Naver Token API
    String access_token;
    String refresh_token;
    String token_type;
    String expires_in;
    // couldn't set yet
    String area;
    int manner;
}
