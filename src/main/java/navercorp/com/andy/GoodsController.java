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
    FileStorageService fileService;

    final String REDIRECT_TO_HOME = "redirect:/goods";

    final String TEMPLATE_HOME = "home";
    final String TEMPLATE_ITEM = "item";

    @GetMapping("/")
    public String getGoods() {
        return REDIRECT_TO_HOME;
    }

    @GetMapping("/goods")
    public String getGoods(Model model, @RequestParam String code, @RequestParam String state) {

        System.out.println("code : " + code);
        System.out.println("state : " + state);

        model.addAttribute("list", goodsService.getDummy());
        if (!state.equals(NaverConfiguration.VAL.state)) {
            System.out.println("error! strange access trial");
        } else if (!code.isEmpty() && !state.isEmpty()){

            Token token = null;
            Profile profile = null;

            do {
                token = AccessTokenAPI.get(code);
                if (token == null) {
                    System.out.println("break due to null token");
                    break;
                }
                System.out.println(token);
                System.out.println(token.getAccess_token());
                System.out.println(token.getRefresh_token());
                System.out.println(token.getToken_type());
                System.out.println(token.getExpires_in());

                profile = ProfileAPI.get(token.getAccess_token());
                if (profile == null) {
                    System.out.println("break due to null profile");
                    break;
                }
                System.out.println(profile);
                System.out.println(profile.getId());
                System.out.println(profile.getName());
                System.out.println(profile.getNickname());
                System.out.println(profile.getProfile_image());
            } while (false);
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
