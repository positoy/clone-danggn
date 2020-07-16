package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.GoodsListItem;
import navercorp.com.andy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;

@Controller
public class GoodsController {

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/goods")
    public String getGoods(Model model) {
        ArrayList<GoodsListItem> list = goodsRepository.getDummy();
        model.addAttribute("list", list);
        return "home";
    }

    @GetMapping("/goods/{id}")
    public String getGoods(@PathVariable String id, Model model) {
        System.out.println("goods id : " + id);
        // TODO : add user field inside the goods and get a user from the goods
        Goods goods = goodsRepository.getGoods(id);
        User user = userRepository.getUser(goods.getUserid());

        // TODO handle null exception
        if (goods == null || user == null)
            System.out.println("null error has occured.");
        System.out.println(goods);
        System.out.println(user);
        model.addAttribute("goods", goods);
        model.addAttribute("user", user);
        return "item";
    }

    @PostMapping("/goods")
    public String postGoods(Goods body) {
        System.out.println(body);
        return "redirect:/goods";
    }
}
