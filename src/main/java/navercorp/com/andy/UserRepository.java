package navercorp.com.andy;

import java.util.ArrayList;
import navercorp.com.andy.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    ArrayList<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("unknown", "안알랴줌", "달나라", "/img/user/0.png", 368));
        users.add(new User("0", "앤디", "가동", "/img/user/0.png", 368));
        users.add(new User("1", "브리트니", "나동", "/img/user/1.jpg", 468));
        users.add(new User("2", "디미트리", "다동", "/img/user/2.jpg", 568));
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
