package navercorp.com.andy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    final String TEMPLATE_CATEGORY = "category";
    final String TEMPLATE_CHAT = "chat";
    final String TEMPLATE_USER = "user";

    @GetMapping("/category")
    public String getCategory(@RequestParam(defaultValue = "") String userid, Model model) {
        if (!userid.isEmpty())
            model.addAttribute("user", userRepository.getUser(userid));
        return TEMPLATE_CATEGORY;
    }

    @GetMapping("/chat")
    public String getChat(@RequestParam(defaultValue = "")String userid, Model model) {
        if (!userid.isEmpty())
            model.addAttribute("user", userRepository.getUser(userid));
        return TEMPLATE_CHAT;
    }

    @GetMapping("/user")
    public String getUser(@RequestParam(defaultValue = "")String userid, Model model) {
        if (!userid.isEmpty())
            model.addAttribute("user", userRepository.getUser(userid));
        return TEMPLATE_USER;
    }
}
