package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    final String ERROR_REDIRECT_TO_HOME = "redirect:/goods";
    final String OKAY_REDIRECT_TO_HOME = "redirect:/goods";

    final String TEMPLATE_HOME = "home";
    final String TEMPLATE_ITEM = "item";

    @GetMapping("/goods")
    public String getGoods(Model model) {
        model.addAttribute("list", goodsService.getDummy());
        return TEMPLATE_HOME;
    }

    @GetMapping("/goods/{id}")
    public String getGoods(@PathVariable String id, Model model) {
        System.out.println(id);
        model.addAttribute("goods", goodsService.getGoods(id));
        model.addAttribute("user", goodsService.getUser(goodsService.getGoods((id)).getUserid()));

        if (model.getAttribute("goods") == null || model.getAttribute("user") == null) {
            System.out.println("goods or user is null");
            return ERROR_REDIRECT_TO_HOME;
        }
        return TEMPLATE_ITEM;
    }

    @PostMapping("/goods")
    public String postGoods(Goods good) {
        System.out.println(good);
        goodsService.saveGoods(good);

        return OKAY_REDIRECT_TO_HOME;
    }
}
