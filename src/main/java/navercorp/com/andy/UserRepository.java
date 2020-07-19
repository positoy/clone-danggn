package navercorp.com.andy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import navercorp.com.andy.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    Map<String, User> users = new HashMap<>();

    public UserRepository() {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("unknown", "안알랴줌", "/img/user/0.png", "알랴줌", "", "", "", "", "달나라", 368));
        list.add(new User("0", "앤디", "/img/user/0.png", "앤", "", "", "", "", "광교", 368));
        list.add(new User("1", "브리트니", "/img/user/1.png", "브리", "", "", "", "", "수지", 368));
        list.add(new User("2", "디미트리", "/img/user/2.png", "디미", "", "", "", "", "판교", 368));

        list.forEach(user -> {
            users.put(user.getId(), user);
        });
    }

    public User getUser(String id) {
        User user = users.get(id);
        if (user == null) {
            System.out.println("Couldn't find any user with the id, " + id);
        }
        return user;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }
}
