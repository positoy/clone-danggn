package navercorp.com.andy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
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

    @Column(length = 20)
    String nickname;

    @Column(length = 100)
    String profile_image;

    @Column(length = 20)
    String name;

    // Naver Token API
    @Column(length = 200)
    String access_token;

    @Column(length = 200)
    String refresh_token;

    @Column(length = 200)
    String token_type;

    @Column(length = 200)
    String expires_in;

    // couldn't set yet
    @Column(length = 20)
    String area;

    @Column
    int manner;
}
