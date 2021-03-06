package navercorp.com.andy.naver;

import lombok.ToString;

@ToString
public class Profile {
    final public Long id;
    final public String name;
    final public String nickname;
    final public String profile_image;

    public Profile(Long id, String name, String nickname, String profile_image) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.profile_image = profile_image;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfile_image() {
        return profile_image;
    }
}