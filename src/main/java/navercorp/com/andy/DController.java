package navercorp.com.andy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DController {

    @PostMapping("/goods")
    public String postGoods(Goods body) {
        System.out.println(body);
        return "redirect:/item.html";
    }
}
