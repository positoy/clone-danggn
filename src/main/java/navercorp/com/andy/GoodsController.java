package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    FileStorageService fileService;

    final String REDIRECT_TO_HOME = "redirect:/goods";

    final String TEMPLATE_HOME = "home";
    final String TEMPLATE_ITEM = "item";

    @GetMapping("/")
    public String getGoods() {
        return REDIRECT_TO_HOME;
    }

    @GetMapping("/goods")
    public String getGoods(Model model) {
        model.addAttribute("list", goodsService.getDummy());
        return TEMPLATE_HOME;
    }

    @GetMapping("/goods/{id}")
    public String getGoods(@PathVariable String id, Model model) {
        System.out.println(id);
        model.addAttribute("goods", goodsService.getGoods(id));
        model.addAttribute("user", goodsService.getUserWithGoodsId(id));

        if (model.getAttribute("goods") == null || model.getAttribute("user") == null) {
            System.out.println("goods or user is null");
            return REDIRECT_TO_HOME;
        }
        return TEMPLATE_ITEM;
    }

    @PostMapping("/goods")
    @ResponseBody
    public String postGoods(Goods good, @RequestParam MultipartFile pics, RedirectAttributes redirectAttributes) {
        System.out.println(good);
        System.out.println(pics.getOriginalFilename());
        fileService.store(pics);
        goodsService.saveGoods(good);

        return REDIRECT_TO_HOME;
    }

}
