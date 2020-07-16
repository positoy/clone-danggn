package navercorp.com.andy;

import java.util.ArrayList;
import navercorp.com.andy.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    ArrayList<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("0", "박박박", "신갈동", "/img/user/0.png", 368));
        users.add(new User("0", "박박박", "신갈동", "/img/user/0.png", 368));
        users.add(new User("0", "박박박", "신갈동", "/img/user/0.png", 368));
        users.add(new User("0", "박박박", "신갈동", "/img/user/0.png", 368));
        users.add(new User("0", "박박박", "신갈동", "/img/user/0.png", 368));
    }

    public User getUser(String id) {
        for (User user : users) {
            if (user.getId().equals(id))
                return user;
        }
        System.out.println("Couldn't find any user with the id, " + id);
        return null;
    }
}
