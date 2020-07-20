package navercorp.com.andy;

import navercorp.com.andy.DAO.UserRepository;
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

    public User getUserById(Long userid) {
        User user = null;
        try {
            user = userRepository.findById(userid).get();
        } catch (Exception e) {
            logger.warn("not found with userid : " + userid, e.getMessage());
        }

        return user;
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
            } else {
                logger.info(token.toString());
            }

            profile = ProfileAPI.get(token.getAccess_token());
            if (profile == null) {
                logger.info("break due to null profile");
                break;
            } else {
                logger.info(profile.toString());
            }

        } while (false);

        if (token == null || profile == null) {
            logger.info("failed to get token or profile");
            return null;
        }

        User user = null;
        try {
            user = userRepository.findById(profile.getId()).get();
        } catch (Exception e) {
            logger.warn("not found with userid : " + profile.getId());
            user = new User(profile.getId(), profile.getNickname(), profile.getProfile_image(), profile.getName(),
                    token.getAccess_token(), token.getRefresh_token(), token.getToken_type(), token.getExpires_in(), "전체", 368);
            user = userRepository.save(user);
        }

        logger.info(user.getId().toString());

        return user;
    }

}
