package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.GoodsListItem;
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
    GoodsRepository repository;

    @GetMapping("/goods")
    public String getGoods(Model model) {
        ArrayList<GoodsListItem> list = repository.getDummy();
        model.addAttribute("list", list);
        return "home";
    }

    @GetMapping("/goods/{id}")
    public String getGoods(@PathVariable String id, Model model) {
        System.out.println("goods id : " + id);
        Goods goods = repository.getGoods(id);
        System.out.println(goods);
        model.addAttribute("goods", goods);
        return "item";
    }

    @PostMapping("/goods")
    public String postGoods(Goods body) {
        System.out.println(body);
        return "redirect:/goods";
    }
}
