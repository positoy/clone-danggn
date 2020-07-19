package navercorp.com.andy;

import navercorp.com.andy.model.User;
import navercorp.com.andy.naver.AccessTokenAPI;
import navercorp.com.andy.naver.Profile;
import navercorp.com.andy.naver.ProfileAPI;
import navercorp.com.andy.naver.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String code) {

        boolean ret = false;

        Token token = null;
        Profile profile = null;
        do {
            token = AccessTokenAPI.get(code);
            if (token == null) {
                System.out.println("break due to null token");
                break;
            }
            System.out.println(token);
            System.out.println(token.getAccess_token());
            System.out.println(token.getRefresh_token());
            System.out.println(token.getToken_type());
            System.out.println(token.getExpires_in());

            profile = ProfileAPI.get(token.getAccess_token());
            if (profile == null) {
                System.out.println("break due to null profile");
                break;
            }
            System.out.println(profile);
            System.out.println(profile.getId());
            System.out.println(profile.getName());
            System.out.println(profile.getNickname());
            System.out.println(profile.getProfile_image());

        } while (false);

        if (token == null || profile == null) {
            System.out.println("failed to get token or profile");
            return null;
        }

        User user = userRepository.getUser(profile.getId());
        if (user == null) {
            user = new User(profile.getId(), profile.getNickname(), profile.getProfile_image(), profile.getName(),
                    token.getAccess_token(), token.getRefresh_token(), token.getToken_type(), token.getExpires_in(), "", 368);
            userRepository.addUser(user);
        }

        return user;
    }

}
