package navercorp.com.andy;

import navercorp.com.andy.model.Goods;
import navercorp.com.andy.model.User;
import navercorp.com.andy.naver.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class GoodsController {

    final static Logger logger = LoggerFactory.getLogger(GoodsController.class);

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

        logger.info("code : " + code);
        logger.info("state : " + state);

        model.addAttribute("list", goodsService.getDummy());

        if (!state.equals(NaverConfiguration.VAL.state)) {
            logger.info("/goods - (not login)");
        } else if (!code.isEmpty()){
            logger.info("/goods - (naver login : authcode " + code + ")");
            model.addAttribute("user", userService.getUser(code));
        }

        return TEMPLATE_HOME;
    }

    @GetMapping("/goods/{id}")
    public String getGoods(@PathVariable String id, Model model, @RequestParam(defaultValue = "") String userid) {
        logger.info(id);
        model.addAttribute("goods", goodsService.getGoods(id));
        logger.info(((Goods)model.getAttribute("goods")).toString());
        model.addAttribute("goodsuser", goodsService.getUserWithGoodsId(id));
        logger.info(((User)model.getAttribute("user")).toString());
        if (!userid.isEmpty())
            model.addAttribute("user", userService.getUserById(userid));

        if (model.getAttribute("goods") == null || model.getAttribute("user") == null) {
            logger.info("goods or user is null");
            return REDIRECT_TO_HOME;
        }
        return TEMPLATE_ITEM;
    }

    @PostMapping("/goods")
    @ResponseBody
    public String postGoods(Goods good, @RequestParam MultipartFile pics, RedirectAttributes redirectAttributes) {
        logger.info(good.toString());
        good = goodsService.refineGoods(good);
        logger.info(good.toString());

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
