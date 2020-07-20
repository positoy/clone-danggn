package navercorp.com.andy;

import navercorp.com.andy.DAO.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserRepository userRepository;

    final String TEMPLATE_CATEGORY = "category";
    final String TEMPLATE_WRITE = "write";
    final String TEMPLATE_CHAT = "chat";
    final String TEMPLATE_USER = "user";

    @GetMapping("/category")
    public String getCategory(@RequestParam(defaultValue = "") String userid, Model model, HttpServletRequest request) {

        Util.updateModelUserFromSession(request, model);
        return TEMPLATE_CATEGORY;
    }

    @GetMapping("/write")
    public String getWrite(@RequestParam(defaultValue = "") String userid, Model model, HttpServletRequest request) {

        Util.updateModelUserFromSession(request, model);
        return TEMPLATE_WRITE;
    }

    @GetMapping("/chat")
    public String getChat(@RequestParam(defaultValue = "")String userid, Model model, HttpServletRequest request) {

        Util.updateModelUserFromSession(request, model);
        return TEMPLATE_CHAT;
    }

    @GetMapping("/user")
    public String getUser(@RequestParam(defaultValue = "")String userid, Model model, HttpServletRequest request) {

        Util.updateModelUserFromSession(request, model);
        return TEMPLATE_USER;
    }
}
