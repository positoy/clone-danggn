package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.User;
import navercorp.com.andy.naver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    UserService userService;

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
    public String getGoods(Model model, @RequestParam(defaultValue = "") String code, @RequestParam(defaultValue = "") String state) {

        System.out.println("code : " + code);
        System.out.println("state : " + state);

        model.addAttribute("list", goodsService.getDummy());
        if (!state.equals(NaverConfiguration.VAL.state)) {
            System.out.println("error! strange access trial");
        } else if (!code.isEmpty()){
            User user = userService.getUser(code);
            if (user != null) {
                model.addAttribute("user", user);
            } else {
                System.out.println("failed to get user information from the authtoken");
            }
        }

        return TEMPLATE_HOME;
    }

    @GetMapping("/goods/{id}")
    public String getGoods(@PathVariable String id, Model model) {
        System.out.println(id);
        model.addAttribute("goods", goodsService.getGoods(id));
        System.out.println((Goods)model.getAttribute("goods"));
        model.addAttribute("user", goodsService.getUserWithGoodsId(id));
        System.out.println((User)model.getAttribute("user"));

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
        good = goodsService.refineGoods(good);
        System.out.println(good);

        if (!pics.getOriginalFilename().isEmpty()) {
            String filename = fileService.generateFileName(pics, good.getTimestamp(), good.getUserid());
            fileService.storeGoodsImg(pics, filename);

            ArrayList<String> imgs = new ArrayList<>();
            String filepath = fileService.generateGoodsImgPath(filename);
            imgs.add(filepath);
            goodsService.setImgs(good, imgs);
        }

        goodsService.saveGoods(good);

        return REDIRECT_TO_HOME;
    }

}
