package navercorp.com.andy;

import navercorp.com.andy.model.User;
import navercorp.com.andy.naver.AccessTokenAPI;
import navercorp.com.andy.naver.Profile;
import navercorp.com.andy.naver.ProfileAPI;
import navercorp.com.andy.naver.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public User getUserById(String userid) {
        return userRepository.getUser(userid);
    }

    public User getUser(String code) {

        boolean ret = false;

        Token token = null;
        Profile profile = null;
        do {
            token = AccessTokenAPI.get(code);
            if (token == null) {
                logger.info("break due to null token");
                break;
            }
            logger.info(token.toString());
            logger.info(token.getAccess_token());
            logger.info(token.getRefresh_token());
            logger.info(token.getToken_type());
            logger.info(token.getExpires_in());

            profile = ProfileAPI.get(token.getAccess_token());
            if (profile == null) {
                logger.info("break due to null profile");
                break;
            }
            logger.info(profile.toString());
            logger.info(profile.getId());
            logger.info(profile.getName());
            logger.info(profile.getNickname());
            logger.info(profile.getProfile_image());

        } while (false);

        if (token == null || profile == null) {
            logger.info("failed to get token or profile");
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
